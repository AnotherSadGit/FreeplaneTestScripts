import javax.swing.*

// Dialog code given in some of the Freeplane example scripts.

// for cut 'n paste:
def showDialog(String text) 
{
    def dialog = new JDialog(ui.frame)
    dialog.setSize(350, 450)
    dialog.setLocationRelativeTo(ui.frame)
    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)
    dialog.add(new JScrollPane(new JTextArea(text)))
    ui.addEscapeActionToDialog(dialog)
    dialog.setVisible(true)
}

def root = node.map.root
def level1Nodes = root.children

def rootID = root.id
def level1Node1 = level1Nodes[0]
def parentNodeID = level1Node1.parent.id
boolean countHidden = true
int nodeLevel = level1Node1.getNodeLevel(countHidden)

showDialog("Root node ID: " + rootID + "\n"
            + "Root node ID via child: " + parentNodeID + "\n"
            + "Child Node Level: " + nodeLevel + "\n")
