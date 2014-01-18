// See http://freeplane.sourceforge.net/doc/api/ org.freeplane.core.ui.components class UITools

// Displays a single textbox with a label and default text.
// Title: "Input"
// Buttons: OK and Cancel.
// Appears under the scripting API > Utilities > UITools > showInputDialog
def filename = ui.showInputDialog(node.delegate, "Text box label", "Default")

// Displays a message as a label.
// Title: "Freeplane"
// Icon: "i"
// Buttons: OK
ui.informationMessage("Output of Input Dialog: " + filename)

// Displays a message as a label, with a title.
// Title: "This is the Title"
// Icon: "i"
// Buttons: OK
def title = "This is the Title"
def message = "This is my message"
ui.informationMessage(ui.frame, message, title)

// Does message box expand to fit the text?
// ANSWER: Yes
def message2 = " Line 1\n Line 2\n Line3\n Line4\n Line5"
ui.informationMessage(ui.frame, message2, title)
