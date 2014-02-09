// Demonstration of importing a compiled class that, in turn, imports Freeplane libraries.

import branchformatter.ColorSequence

def result = ColorSequence.WHEEL
def title = "Import Test: Compiled Enum"
def message = "Enum value: " + result
ui.informationMessage(ui.frame, message, title)