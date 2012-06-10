package booster

import scala.sys.process.Process
import scala.sys.process.stringToProcess
import scala.sys.process.ProcessLogger

class Device() {
  var process: Option[Process] = None

  val command = ("java " +
    "-cp libs/scala-library-2.9.1.jar;libs/bridj-0.6.1.jar;libs/javacl-1.0.0-RC2.jar;libs/javacl-core-1.0.0-RC2.jar;libs/nativelibs4java-utils-1.4.jar;libs/opencl4java-1.0.0-RC2.jar;libs/child.jar " +
    "booster.child.OpenCLDeviceStart")

  def enable {
    process match {
      case None => {
        try {
          val proc = stringToProcess(command).run(ProcessLogger({ println(_) }))
          process = Some(proc)
        } catch {
          case e: Exception => e.printStackTrace()
        }
      }
      case Some(x) => ()
    }
  }

  def disable {
    process match {
      case None => ()
      case Some(proc) => {
        proc.destroy()
        process = None
      }
    }
  }

  def isEnabled: Boolean = process match {
    case None => false
    case Some(x) => true
  }
}

