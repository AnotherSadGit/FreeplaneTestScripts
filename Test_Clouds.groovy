import org.freeplane.plugin.script.proxy.Proxy

import org.freeplane.features.cloud.CloudController;
import org.freeplane.features.cloud.mindmapmode.MCloudController;

class NodeCloud
{
	static void clearCloud(Proxy.Node node)
	{
		setCloud(node, false)
	}
	
	static void setCloud(Proxy.Node node)
	{
		setCloud(node, true)
	}

	// Set to true to add a cloud, false to remove a cloud from selected node.
	//	Is idempotent: Attempting to add a cloud if one already exists, or 
	//	remove one if no cloud exists, has no effect (doesn't add second 
	//	cloud and causes no error).	
	static void setCloud (Proxy.Node node, enableCloud)
    {
		final MCloudController cloudController = 
			(MCloudController) CloudController.getController()
		cloudController.setCloud(node.delegate, enableCloud)
    }
}

NodeCloud.clearCloud(node)
