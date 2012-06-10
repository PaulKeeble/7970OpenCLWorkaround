package booster
import scala.swing.SimpleSwingApplication
import scala.swing.MainFrame
import scala.swing.Button
import scala.swing.Action
import scala.swing.BoxPanel
import scala.swing.Orientation.Vertical
import scala.swing.TextField

object GUIStart extends SimpleSwingApplication {
  val device = new Device()
  
  def top = new MainFrame {
    title = "2D booster"

    val boostButton= new Button {   
      action = Action("Boost"){
        device.isEnabled match {
          case false => {
        	device.enable
          }
          case true => {
        	device.disable
          }
        }
        text = device.isEnabled match {
          case false => {
            "Boost"
          }
          case true => {
            "Turn off"
          }
        }
      }
    }
    
    val deviceName = new TextField(device.toString()) {
      editable = false
    }
    
    contents = new BoxPanel(Vertical) {
      contents += deviceName
      contents += boostButton
    }
  }
}