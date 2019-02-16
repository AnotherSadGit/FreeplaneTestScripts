// @ExecutionModes({ON_SINGLE_NODE})

import org.freeplane.plugin.script.proxy.Proxy

// Shows the node background colour code in a popup info box.
void displayNodeBackgroundColor(Proxy.Node nodeToRead)
{
    def nodeStyle = nodeToRead.style
	def backgroundColorCode = nodeStyle.backgroundColorCode
	
	def cloudBackgroundColourCode = "[NOT SET]"
	if (nodeToRead.cloud.enabled)
	{
		cloudBackgroundColourCode = nodeToRead.cloud.colorCode
	}
	
	ui.informationMessage("Colour codes:\n" + "Node background: " + backgroundColorCode + "\nCloud: " + cloudBackgroundColourCode + "\nMap background: " + nodeToRead.map.backgroundColorCode)
}

displayNodeBackgroundColor(node)