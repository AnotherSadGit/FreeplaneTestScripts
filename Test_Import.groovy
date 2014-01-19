// Two formats for importing static methods of a class:

//  1) import static package.Class.function
//  Usage: def result = function(...)
//  eg
//  import myfun.MyFunctions.square
//  def result = square(2)

// 1b) Alternatively, can import all the functions at once:
//  import static package.Class.*

// 2) import package.Class
//  Usage: def result = Class.function(...)
//  eg 
//  import myfun.MyFunctions
//  def result = MyFunctions.square(2)

// NOTE: Package name is not just the name of the sub-folder the class 
//  is compiled into.  It is also the package name specified in the class.
//  Changing the sub-folder name and the package name in this script 
//  will not work, even if Freeplane is restarted.  Instead, you would 
//  have to change the package name in the class and recompile it.
import static myfun.MyFunctions.square

def result = square(2)
def title = "Import Test"
def message = "2 Squared = " + result
ui.informationMessage(ui.frame, message, title)