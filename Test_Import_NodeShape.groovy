// Demonstration of importing a compiled class that, in turn, imports Freeplane libraries.

import branchformatter.NodeShape

def result = NodeShape.getShapeStyle(node)
def title = "Import Test 2"
def message = "Node Shape: " + result
ui.informationMessage(ui.frame, message, title)