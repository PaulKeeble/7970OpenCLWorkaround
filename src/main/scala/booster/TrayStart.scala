package booster
import java.awt.SystemTray
import java.awt.TrayIcon
import java.awt.Toolkit
import java.awt.PopupMenu
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import java.awt.MenuItem

object TrayStart {
  val device = new Device()
  device.enable

  def main(args: Array[String]): Unit = {
    val systemTray = SystemTray.getSystemTray()
    val trayImage = Toolkit.getDefaultToolkit().createImage("trayicon.png")
    val icon = new TrayIcon(trayImage, "2D Booster 1.0", popup)

    systemTray.add(icon)
  }

  def popup = {
    val menu = new PopupMenu
    menu.add(MenuItem("Boost", { _ => device.enable }))
    menu.add(MenuItem("Disable", { _ => device.disable }))
    menu.add(MenuItem("Exit", { _ => exit }))
    menu
  }
  
  def MenuItem(name:String, f : Function1[ActionEvent,Unit]): MenuItem = {
    val menuItem = new MenuItem(name)
    menuItem.addActionListener(action(f))
    menuItem
  }

  def exit = {
    device.disable
    System.exit(0);
  }

  def action(f: Function1[ActionEvent, Unit]) = {
    new ActionListener() {
      def actionPerformed(e: ActionEvent) {
        f(e)
      }
    }
  }
}