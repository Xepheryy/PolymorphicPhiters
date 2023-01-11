package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.ChatBot.ChatBot;
import com.mygdx.game.ChatBot.ChatBotDao;
import com.mygdx.game.PolymorphicPhiters;

/** Represents the Help screen */
public class HelpScreen extends AbstractScreen {
  private Table responses;
  private String question;
  private final ChatBot chatBot;
  private boolean answering;
  private int cw, ch;
  private LabelStyle labelStyle;

  public HelpScreen(PolymorphicPhiters game) {
    super(game);
    question = "";
    chatBot = ChatBotDao.getChatHistory();
  }

  @Override
  public void inputHandler() {
    if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
      ChatBotDao.saveChatHistory(chatBot);
      game.setScreen(new StartScreen(this.game));
      dispose();
    }
  }

  @Override
  public void show() {
    Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

    cw = Gdx.graphics.getWidth();
    ch = Gdx.graphics.getHeight();

    responses = new Table();
    Table userInterface = new Table();
    Table questions = new Table();
    userInterface.setFillParent(true);
    questions.setFillParent(true);
    labelStyle = new LabelStyle(game.getBitmapFont(), null);
    Label title = new Label("Help Menu", labelStyle);
    title.setAlignment(Align.center);

    userInterface.row();
    userInterface
        .add(title)
        .colspan(3)
        .spaceBottom(ch * 0.1f)
        .width(Gdx.graphics.getWidth() * 0.3f)
        .height(ch * 0.05f);
    userInterface.row();
    Label questionHeader = new Label("Question", labelStyle);
    Label answerHeader = new Label("Answer", labelStyle);
    userInterface.add(questionHeader).width(cw * 0.3f).height(ch * 0.05f);
    userInterface.add(answerHeader).width(cw * 0.3f).height(ch * 0.05f);
    userInterface.row();

    userInterface.row();
    userInterface.align(Align.top);

    Label userInputLabel = new Label("Ask a question: ", labelStyle);
    userInputLabel.setWrap(true);
    TextField userInput = new TextField("", skin);
    userInput.setTextFieldListener(
        new TextField.TextFieldListener() {

          @Override
          public void keyTyped(TextField textField, char c) {
            if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
              if (answering) {
                String answer = textField.getText();
                chatBot.saveResponse(question, answer);
                answering = false;
                question = "";
                return;
              }
              question = textField.getText();
            }
          }
        });

    questions.add(userInputLabel).width(cw * 0.2f);
    questions.add(userInput).width(cw * 0.3f).height(ch * 0.05f);
    questions.align(Align.bottom);

    getStage().addActor(userInterface);
    getStage().addActor(questions);
    getStage().addActor(responses);
    Gdx.input.setInputProcessor(getStage());
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(0, 0, 0, 1);
    if (question.length() > 0) {
      String answer = chatBot.getResponse(question);
      Label questionAsked = new Label(question, labelStyle);
      Label answerLabel = new Label(answer, labelStyle);
      questionAsked.setWrap(true);
      answerLabel.setWrap(true);
      responses.clear();
      responses = new Table();
      responses.setFillParent(true);
      responses.defaults().width(cw * 0.3f).height(ch * 0.05f);
      if (answer != null) {
        responses.add(questionAsked);
        responses.add(answerLabel);
        responses.row().spaceBottom(ch * 0.3f);
        getStage().addActor(responses);
      } else {
        answerLabel =
            new Label(
                "We aren't able to answer that at the moment. If you do know the answer, input it"
                    + " into the question prompt.",
                labelStyle);
        answerLabel.setWrap(true);
        responses.add(questionAsked);
        responses.add(answerLabel);
        answering = true;
        getStage().addActor(responses);
      }
    }
    getStage().act(delta);
    getStage().draw();
    inputHandler();
  }

  @Override
  public void resize(int width, int height) {
    getStage().getViewport().update(width, height);
  }

  @Override
  public void pause() {}

  @Override
  public void resume() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {}
}
