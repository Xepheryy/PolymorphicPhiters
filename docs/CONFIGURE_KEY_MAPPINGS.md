# Configuring `key_mappings.json`

## Format

When the game is launched for the first time, a default configuration is saved to `key_mappings.json`.

The default configuration saved is as follows:
```json
{
"playerOneKeyMapping": {
	"left": "A",
	"right": "D",
	"jump": "W",
	"crouch": "S",
	"attack": "Q",
	"special": "E"
},
"playerTwoKeyMapping": {
	"left": "Left",
	"right": "Right",
	"jump": "Up",
	"crouch": "Down",
	"attack": "R-Alt",
	"special": "R-Ctrl"
}
}
```

As can be seen, there are two parts to the configuration:
- The actual key bindings for the various actions in the game
- The player the key bindings correspond to

To configure the key bindings, change the keyboard values accordingly.

For example, to change the key binding for the `special` action for player one to `spacebar`, the file will be changed like so:
```json
{
"playerOneKeyMapping": {
	"left": "A",
	"right": "D",
	"jump": "W",
	"crouch": "S",
	"attack": "Q",
	"special": "Space"  // modified key binding
},
"playerTwoKeyMapping": {
	"left": "Left",
	"right": "Right",
	"jump": "Up",
	"crouch": "Down",
	"attack": "R-Alt",
	"special": "R-Ctrl"
}
}
```

## Values to use for key binding
<details>
<summary>
The following denotes the values to be used in <code>key_mappings.json</code> to customise the keyboard controls used in the game. The value to be used for the specified key is stated on the right in quotes.
</summary>

```
NUM_0: "0"
NUM_1: "1"
NUM_2: "2"
NUM_3: "3"
NUM_4: "4"
NUM_5: "5"
NUM_6: "6"
NUM_7: "7"
NUM_8: "8"
NUM_9: "9"
A: "A"
ALT_LEFT: "L-Alt"
ALT_RIGHT: "R-Alt"
APOSTROPHE: "'"
AT: "@"
B: "B"
BACK: "Back"
BACKSLASH: "\"
C: "C"
CALL: "Call"
CAMERA: "Camera"
CAPS_LOCK: "Caps Lock"
CLEAR: "Clear"
COMMA: ","
D: "D"
DEL: "Delete"
BACKSPACE: "Delete"
FORWARD_DEL: "Forward Delete"
DPAD_CENTER: "Center"
DPAD_DOWN: "Down"
DPAD_LEFT: "Left"
DPAD_RIGHT: "Right"
DPAD_UP: "Up"
CENTER: "Center"
DOWN: "Down"
LEFT: "Left"
RIGHT: "Right"
UP: "Up"
E: "E"
ENDCALL: "End Call"
ENTER: "Enter"
ENVELOPE: "Envelope"
EQUALS: "="
EXPLORER: "Explorer"
F: "F"
FOCUS: "Focus"
G: "G"
GRAVE: "`"
H: "H"
HEADSETHOOK: "Headset Hook"
HOME: "Home"
I: "I"
J: "J"
K: "K"
L: "L"
LEFT_BRACKET: "["
M: "M"
MEDIA_FAST_FORWARD: "Fast Forward"
MEDIA_NEXT: "Next Media"
MEDIA_PLAY_PAUSE: "Play/Pause"
MEDIA_PREVIOUS: "Prev Media"
MEDIA_REWIND: "Rewind"
MEDIA_STOP: "Stop Media"
MENU: "Menu"
MINUS: "-"
MUTE: "Mute"
N: "N"
NOTIFICATION: "Notification"
NUM: "Num"
O: "O"
P: "P"
PAUSE: "Pause"
PERIOD: "."
PLUS: "Plus"
POUND: "#"
POWER: "Power"
PRINT_SCREEN: "Print"
Q: "Q"
R: "R"
RIGHT_BRACKET: "]"
S: "S"
SCROLL_LOCK: "Scroll Lock"
SEARCH: "Search"
SEMICOLON: ";"
SHIFT_LEFT: "L-Shift"
SHIFT_RIGHT: "R-Shift"
SLASH: "/"
SOFT_LEFT: "Soft Left"
SOFT_RIGHT: "Soft Right"
SPACE: "Space"
STAR: "*"
SYM: "SYM"
T: "T"
TAB: "Tab"
U: "U"
UNKNOWN: "Unknown"
V: "V"
VOLUME_DOWN: "Volume Down"
VOLUME_UP: "Volume Up"
W: "W"
X: "X"
Y: "Y"
Z: "Z"
META_ALT_LEFT_ON: "9"
META_ALT_ON: "Soft Right"
META_ALT_RIGHT_ON: "D"
META_SHIFT_LEFT_ON: "Explorer"
META_SHIFT_ON: "Soft Left"
META_SHIFT_RIGHT_ON: "null"
META_SYM_ON: "Back"
CONTROL_LEFT: "L-Ctrl"
CONTROL_RIGHT: "R-Ctrl"
ESCAPE: "Escape"
END: "End"
INSERT: "Insert"
PAGE_UP: "Page Up"
PAGE_DOWN: "Page Down"
PICTSYMBOLS: "PICTSYMBOLS"
SWITCH_CHARSET: "SWITCH_CHARSET"
BUTTON_CIRCLE: "null"
BUTTON_A: "A Button"
BUTTON_B: "B Button"
BUTTON_C: "C Button"
BUTTON_X: "X Button"
BUTTON_Y: "Y Button"
BUTTON_Z: "Z Button"
BUTTON_L1: "L1 Button"
BUTTON_R1: "R1 Button"
BUTTON_L2: "L2 Button"
BUTTON_R2: "R2 Button"
BUTTON_THUMBL: "Left Thumb"
BUTTON_THUMBR: "Right Thumb"
BUTTON_START: "Start"
BUTTON_SELECT: "Select"
BUTTON_MODE: "Button Mode"
NUMPAD_0: "Numpad 0"
NUMPAD_1: "Numpad 1"
NUMPAD_2: "Numpad 2"
NUMPAD_3: "Numpad 3"
NUMPAD_4: "Numpad 4"
NUMPAD_5: "Numpad 5"
NUMPAD_6: "Numpad 6"
NUMPAD_7: "Numpad 7"
NUMPAD_8: "Numpad 8"
NUMPAD_9: "Numpad 9"
NUMPAD_DIVIDE: "Num /"
NUMPAD_MULTIPLY: "Num *"
NUMPAD_SUBTRACT: "Num -"
NUMPAD_ADD: "Num +"
NUMPAD_DOT: "Num ."
NUMPAD_COMMA: "Num ,"
NUMPAD_ENTER: "Num Enter"
NUMPAD_EQUALS: "Num ="
NUMPAD_LEFT_PAREN: "Num ("
NUMPAD_RIGHT_PAREN: "Num )"
NUM_LOCK: "Num Lock"
COLON: ":"
F1: "F1"
F2: "F2"
F3: "F3"
F4: "F4"
F5: "F5"
F6: "F6"
F7: "F7"
F8: "F8"
F9: "F9"
F10: "F10"
F11: "F11"
F12: "F12"
F13: "F13"
F14: "F14"
F15: "F15"
F16: "F16"
F17: "F17"
F18: "F18"
F19: "F19"
F20: "F20"
F21: "F21"
F22: "F22"
F23: "F23"
F24: "F24"
```
</details>
