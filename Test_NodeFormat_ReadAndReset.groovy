import javax.swing.*
import org.freeplane.features.nodestyle.NodeStyleModel
import org.freeplane.features.nodestyle.mindmapmode.MNodeStyleController
import org.freeplane.features.mode.Controller
import org.freeplane.features.mode.ModeController
import org.freeplane.features.nodestyle.NodeStyleController
import org.freeplane.plugin.script.proxy.Proxy

// Read the formatting applied to a node and reset it (remove the styles and formatting applied 
//  to the node so it goes back to its default formatting.

def String getShapeStyle(Proxy.Node nodeToRead)
{
	final ModeController modeController = Controller.getCurrentModeController()
    final MNodeStyleController styleController = 
        (MNodeStyleController) modeController.getExtension(NodeStyleController.class);
    return styleController.getShape(nodeToRead.delegate)
}

def setShapeStyleBubble (Proxy.Node nodeToSet)
{
	setShapeStyle(nodeToSet, NodeStyleModel.STYLE_BUBBLE)
}

def setShapeStyleFork(Proxy.Node nodeToSet)
{
	setShapeStyle(nodeToSet, NodeStyleModel.STYLE_FORK)
}

def resetShapeStyle(Proxy.Node nodeToSet)
{
	setShapeStyle(nodeToSet, null)
}
	
def setShapeStyle(Proxy.Node nodeToSet, String style)
{
    final ModeController modeController = Controller.getCurrentModeController()
    final MNodeStyleController styleController = 
        (MNodeStyleController) modeController.getExtension(NodeStyleController.class);
    styleController.setShape(nodeToSet.delegate, style)
}

def child = node.createChild("New child node")
def originalShape = getShapeStyle(child)
setShapeStyleBubble(child)
def modifiedShape = getShapeStyle(child)
resetShapeStyle(child)
def resetShape = getShapeStyle(child)
def title = "ShapeStyle Functions Test"
def message = "Original child shape: " + originalShape + "\n" + 
	"Modified child shape: " + modifiedShape + "\n" + 
	"Reset child shape: " + resetShape + "\n"
ui.informationMessage(ui.frame, message, title)

def root = node.getMap().getRoot()
def nodeStyle = node.style
def styleNode = node.createChild("Style Info")
//styleNode.createChild(nodeStyle.backgroundColor)
styleNode.createChild("backgroundColorCode: " + nodeStyle.backgroundColorCode)
//styleNode.createChild(nodeStyle.edge)
styleNode.createChild("floating: " + nodeStyle.floating)
//styleNode.createChild(nodeStyle.font)
styleNode.createChild("maxNodeWidth: " + nodeStyle.maxNodeWidth)
styleNode.createChild("minNodeWidth: " + nodeStyle.minNodeWidth)
styleNode.createChild("name: " + nodeStyle.name)
//styleNode.createChild(nodeStyle.nodeTextColor)
styleNode.createChild("style: " + nodeStyle.style)
//styleNode.createChild("styleNode: " + nodeStyle.styleNode.toString())
//styleNode.createChild(nodeStyle.textColor)
styleNode.createChild("textColorCode: " + nodeStyle.textColorCode)

def shapeStyleNode = node.createChild("Shape Style: " + getShapeStyle(node))

def nodeEdge = nodeStyle.edge
def edgeNode = styleNode.createChild("Edge Info")
edgeNode.createChild("colorCode: " + nodeEdge.colorCode)
edgeNode.createChild("type (EdgeStyle enum): " + nodeEdge.type)
edgeNode.createChild("width: " + nodeEdge.width)

def nodeFont = nodeStyle.font
def fontNode = styleNode.createChild("Font Info")
fontNode.createChild("bold: " + nodeFont.bold)
fontNode.createChild("italic: " + nodeFont.italic)
fontNode.createChild("name: " + nodeFont.name)
fontNode.createChild("size: " + nodeFont.size)

nodeStyle.backgroundColorCode = null
nodeStyle.floating = false
nodeStyle.maxNodeWidth = -1
nodeStyle.minNodeWidth = -1
nodeStyle.name = null
nodeStyle.textColorCode = null

nodeEdge.colorCode = null
nodeEdge.width = -1

nodeFont.resetBold()
nodeFont.resetItalic()
nodeFont.resetName()
nodeFont.resetSize()

resetShapeStyle(node)