package branchformatter

import org.freeplane.features.edge.EdgeStyle
import org.freeplane.features.nodestyle.NodeStyleModel
import org.freeplane.features.nodestyle.mindmapmode.MNodeStyleController
import org.freeplane.features.mode.Controller
import org.freeplane.features.mode.ModeController
import org.freeplane.features.nodestyle.NodeStyleController
import org.freeplane.plugin.script.proxy.Proxy

class NodeShape
{
    // Only needed so can compile to Java class file.
    static void main(String[] args)
    {
        return
    }
    
    static String getShapeStyle(Proxy.Node nodeToRead)
    {
        final ModeController modeController = Controller.getCurrentModeController()
        final MNodeStyleController styleController = 
            (MNodeStyleController) modeController.getExtension(NodeStyleController.class);
        return styleController.getShape(nodeToRead.delegate)
    }

    static void setShapeStyleBubble (Proxy.Node nodeToSet)
    {
        setShapeStyle(nodeToSet, NodeStyleModel.STYLE_BUBBLE)
    }

    static void setShapeStyleFork(Proxy.Node nodeToSet)
    {
        setShapeStyle(nodeToSet, NodeStyleModel.STYLE_FORK)
    }

    static void resetShapeStyle(Proxy.Node nodeToSet)
    {
        setShapeStyle(nodeToSet, null)
    }
        
    static void setShapeStyle(Proxy.Node nodeToSet, String style)
    {
        final ModeController modeController = Controller.getCurrentModeController()
        final MNodeStyleController styleController = 
            (MNodeStyleController) modeController.getExtension(NodeStyleController.class);
        styleController.setShape(nodeToSet.delegate, style)
    }
}