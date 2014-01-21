// Demonstration of importing a compiled class that, in turn, imports Freeplane libraries.

import static branchformatter.NodeShape.getShapeStyle

def result = getShapeStyle(node)
def title = "Import Test 2"
def message = "Node Shape: " + result
ui.informationMessage(ui.frame, message, title)