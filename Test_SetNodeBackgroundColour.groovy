// @ExecutionModes({ON_SINGLE_NODE})

import org.freeplane.plugin.script.proxy.Proxy

// Applies the specified background colour to the currently selected node.
// Examples: 
//	1) setNodeBackgroundColor(node, null) - clears node background colour
//	2) setNodeBackgroundColor(node, "x00ff00") - sets node background colour to bright green

void setNodeBackgroundColor(Proxy.Node nodeToFormat, String rgbString)
{
    def nodeStyle = nodeToFormat.style
	nodeStyle.backgroundColorCode = rgbString
}

setNodeBackgroundColor(node, null)