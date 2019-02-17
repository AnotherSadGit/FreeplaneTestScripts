// @ExecutionModes({ON_SINGLE_NODE})

import org.freeplane.plugin.script.proxy.Proxy
import org.freeplane.features.mode.Controller
import org.freeplane.features.mode.ModeController
import org.freeplane.features.nodestyle.NodeStyleController
import org.freeplane.features.nodestyle.mindmapmode.MNodeStyleController

// Shows the node background colour code in a popup info box.
void displayNodeBackgroundColor(Proxy.Node nodeToRead)
{
	// Result: 
	// cloud.enabled = true and cloud.colorCode = colour code (eg #f0f0f0) only if a cloud is 
	//	rooted on the selected node.  If the selected node is within a cloud, but the cloud is 
	//	not rooted on it, or if the selected node is not within a cloud, then 
	//	cloud.enabled = false and cloud.colorCode = null
	
	StringBuilder sb = new StringBuilder()
	sb.append("Cloud enabled: " + nodeToRead.cloud.enabled)
	sb.append("\n\n")
	
    def nodeStyle = nodeToRead.style
	
	sb.append("Colour codes:")
	sb.append("\n")	
	
	sb.append("    Node background: " + nodeStyle.backgroundColorCode)
	sb.append("\n")
	sb.append("    Cloud: " + nodeToRead.cloud.colorCode)
	sb.append("\n")
	sb.append("    Map background: " + nodeToRead.map.backgroundColorCode)
	
	ui.informationMessage(sb.toString())
}

//displayNodeBackgroundColor(node)

// Nodes for functions that operate on branches:
//	(if clouds are rooted on the nodes the cloud colours appear under the node text in the diagram below):
/*
																				Create order 11 (cloud #f0f0f0)
																				/
									Create order 4 - Create order 8 - Create order 9
									/				(cloud #ccffcc)				\
					Create order 2												Create order 12
				 /	(cloud #ffcccc)	\
				/					Create order 5 - Create order 10 						Create order 14 - Create order 15
Create order 1 						(cloud #ffffcc)											/
(cloud #f0f0f0)	\				Create order 6 - Create order 18 (inserted) - Create order 13
				 \				/															\
				Create order 3																Create order 17 (16 deleted)
								\
								Create order 7	
*/


// For investigating the order nodes are traversed in by node.findAll() and findAllDepthFirst().
void displayFindAllOrder(Proxy.Node branchRootNode, Boolean doDepthFirst)
{
	// Results:
	
	// 1) findAll():
	/*
		Node traversal order under node with text 'Create order 1':
		Create order 1
		Create order 2
		Create order 4
		Create order 8
		Create order 9
		Create order 11
		Create order 12
		Create order 5
		Create order 10
		Create order 3
		Create order 6
		Create order 18 (inserted)
		Create order 13		
		Create order 14
		Create order 15
		Create order 17 (16 deleted)
		Create order 7
	*/
	// So depth first starting at, and including, the node the branch is rooted on.  Order nodes 
	//	created in is ignored.  Works on branches from the top of the page to the bottom.
	// Moved node "Create order 3" above "Create order 2" and then it traversed the tree rooted 
	//	on "Create order 3" first.
	
	// NOTE: JavaDocs for findAll method say it traverses nodes of branch breadth first.:
	
	// 2) findAllDepthFirst():
	/*
		Node traversal order under node with text 'Create order 1':
		Create order 11
		Create order 12
		Create order 9
		Create order 8
		Create order 4
		Create order 10
		Create order 5
		Create order 2
		Create order 15
		Create order 14
		Create order 17 (16 deleted)
		Create order 13
		Create order 18 (inserted)		
		Create order 6
		Create order 7
		Create order 3
		Create order 1
	*/
	// So reverse depth first starting at the top-most leaf node (closest to the top of the page) 
	//	and working backwards to the node the branch is rooted on.  Order nodes created in is 
	//	ignored.	
	
	StringBuilder sb = new StringBuilder()
	sb.append("Node traversal order under branch rooted on node with text '" + branchRootNode.text + "':")
	sb.append("\n")
	
	def branchNodes = null
	if (doDepthFirst)
	{
		branchNodes = branchRootNode.findAllDepthFirst()
	}
	else
	{
		branchNodes = branchRootNode.findAll()
	}
	
    for (branchNode in branchNodes)
	{
		sb.append(branchNode.text)
		sb.append("\n")
	}
	
	textUtils.copyToClipboard(displayText)
	ui.informationMessage(sb.toString())
}

//displayFindAllOrder(node, false)
//displayFindAllOrder(node, true)

// Shows the colour of the cloud containing each node in a branch.
void displayBranchNodesCloudColor(Proxy.Node branchRootNode)
{	
	// Results:
	/*
		Colours of clouds enclosing nodes of a branch, rooted on node 'Create order 1':
		Create order 1(ID_64017297): #f0f0f0
		Create order 2(ID_851974931): #ffcccc
		Create order 4(ID_126508148): null
		Create order 8(ID_1572411330): #ccffcc
		Create order 9(ID_1743791467): null
		Create order 11(ID_1560062600): #f0f0f0
		Create order 12(ID_1337433104): null
		Create order 5(ID_699972680): #ffffcc
		Create order 10(ID_1873718198): null
		Create order 3(ID_1127681594): null
		Create order 6(ID_564379043): null
		Create order 18 (inserted)(ID_1411373488): null
		Create order 13(ID_12733566): null
		Create order 14(ID_265821463): null
		Create order 15(ID_1776007470): null
		Create order 17 (16 deleted)(ID_1716013617): null
		Create order 7(ID_820236793): null
	*/	
	StringBuilder sb = new StringBuilder()
	sb.append("Colours of clouds enclosing nodes of a branch, rooted on node '" + branchRootNode.text + "':")
	sb.append("\n")
	
	branchNodes = branchRootNode.findAll()
	for (branchNode in branchNodes)
	{
		sb.append(branchNode.text + "(" + branchNode.id + "): " + branchNode.cloud.colorCode)
		sb.append("\n")
	}
	
	def displayText = sb.toString()
	textUtils.copyToClipboard(displayText)
	ui.informationMessage(displayText)
}

//displayBranchNodesCloudColor(node)

// Shows the colour of the cloud containing each node in a branch, take 2.
void displayBranchNodesCloudColor2(Proxy.Node branchRootNode)
{	
	// Results:
	/*
		Colours of clouds enclosing nodes of a branch, rooted on node 'Create order 1':
		Create order 1(ID_64017297): #f0f0f0
		Create order 2(ID_851974931): #ffcccc
		Create order 4(ID_126508148): #ffcccc
		Create order 8(ID_1572411330): #ccffcc
		Create order 9(ID_1743791467): #ccffcc
		Create order 11(ID_1560062600): #f0f0f0
		Create order 12(ID_1337433104): #ccffcc
		Create order 5(ID_699972680): #ffffcc
		Create order 10(ID_1873718198): #ffffcc
		Create order 3(ID_1127681594): #f0f0f0
		Create order 6(ID_564379043): #f0f0f0s
		Create order 18 (inserted)(ID_1411373488): #f0f0f0
		Create order 13(ID_12733566): #f0f0f0
		Create order 14(ID_265821463): #f0f0f0
		Create order 15(ID_1776007470): #f0f0f0
		Create order 17 (16 deleted)(ID_1716013617): #f0f0f0
		Create order 7(ID_820236793): #f0f0f0
	*/	
	StringBuilder sb = new StringBuilder()
	sb.append("Colours of clouds enclosing nodes of a branch, rooted on node '" + branchRootNode.text + "':")
	sb.append("\n")

	HashMap<String, String> nodeCloudColourCodes = new HashMap<String, String>();
	branchNodes = branchRootNode.findAll()
	for (branchNode in branchNodes)
	{
		def nodeCloudColourCode = branchNode.cloud.colorCode
		if (!nodeCloudColourCode)
		{
			def parentNode = branchNode.parent
			if (parentNode && nodeCloudColourCodes.containsKey(parentNode.id))
			{
				nodeCloudColourCode = nodeCloudColourCodes.get(parentNode.id)
			}
		}
		nodeCloudColourCodes.put(branchNode.id, nodeCloudColourCode)
		sb.append(branchNode.text + "(" + branchNode.id + "): " + nodeCloudColourCode)
		sb.append("\n")
	}
	
	def displayText = sb.toString()
	textUtils.copyToClipboard(displayText)
	ui.informationMessage(displayText)
}

//displayBranchNodesCloudColor2(node

// Set node border width same as edge width.
void setNodeBorderWidthMatchesEdgeWidth(Proxy.Node nodeToFormat, Boolean borderWidthMatchesEdgeWidth)
{
    final ModeController modeController = Controller.getCurrentModeController()
	final MNodeStyleController styleController = 
		(MNodeStyleController) modeController.getExtension(NodeStyleController.class)
	styleController.setBorderWidthMatchesEdgeWidth(nodeToFormat.delegate, borderWidthMatchesEdgeWidth)
}

//setNodeBorderWidthMatchesEdgeWidth(node, false)

// Needed for isFontMonoSpaced
import java.awt.Font
import java.awt.font.FontRenderContext
import java.awt.geom.Rectangle2D
import java.awt.RenderingHints

// Based on Stackoverflow answer https://stackoverflow.com/a/17215863/216440 to 
//	question https://stackoverflow.com/questions/922052/testing-whether-a-font-is-monospaced-in-java
Boolean isFontMonoSpaced(String fontName, String defaultCodeFontName)
{
	// Get font at 12 point to avoid errors where character widths for non-mono-spaced fonts 
	//	for narrow and wide characters appear the same at small point sizes.
	// eg GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts() returns fonts at 
	//	1 point size and about 15% of fonts will appear to be mono-spaced at that size when they 
	//	really aren't.
	Font specifiedFont = Font.decode(fontName + "-12")	
	
	// Fall back to less accurate method if can't find font.  This should never happen, see note 
	//	below about "Dialog" font family, but let's be careful in case Java changes things.
	if (!specifiedFont)
	{
		ui.informationMessage("Font with name '" + fontName + "' not found.")
		return (fontName.toLowerCase() == defaultCodeFontName.toLowerCase())
	}

	// If font name not found on system, JavaDocs say Font.decode will return a font from the 
	//	font family "Dialog".
	if (specifiedFont.fontName.toLowerCase() != fontName.toLowerCase())
	{
		ui.informationMessage("Font with name '" + fontName + "' not found.  Font.decode returned font '" 
			+ specifiedFont.fontName + "'.")
		return (fontName.toLowerCase() == defaultCodeFontName.toLowerCase())
	}

	FontRenderContext frc = new FontRenderContext(null, 
		RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT, RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT)
	Rectangle2D iBounds = specifiedFont.getStringBounds("i", frc)
	Rectangle2D spaceBounds = specifiedFont.getStringBounds(" ", frc)
	Rectangle2D mBounds = specifiedFont.getStringBounds("m", frc)
	
	StringBuilder sb = new StringBuilder()
	sb.append("Comparing '" + fontName + "' font character widths:\n")
	sb.append("i: " + iBounds.width + "\n")
	sb.append("[space]: " + spaceBounds.width + "\n")
	sb.append("m: " + mBounds.width + "\n")	
	ui.informationMessage(sb.toString())
	
	return ((iBounds.width == mBounds.width) && (spaceBounds.width == mBounds.width))
}

void displayWhetherFontMonoSpaced(String fontName, Boolean expectedResult)
{
	def defaultCodeFontName = "Consolas"
	def isMonoSpaced = isFontMonoSpaced(fontName, defaultCodeFontName)
	ui.informationMessage("Font '" + fontName + "' is mono-spaced: " + isMonoSpaced 
		+ ". Expected result: " + expectedResult)
}

displayWhetherFontMonoSpaced("Consolas", true)
displayWhetherFontMonoSpaced("Lucida Console", true)
displayWhetherFontMonoSpaced("Arial", false)
displayWhetherFontMonoSpaced("Tahoma", false)
displayWhetherFontMonoSpaced("Invalid", false)
