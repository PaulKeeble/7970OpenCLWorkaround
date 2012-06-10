package booster.child

import com.nativelibs4java.opencl.JavaCL

object OpenCLDeviceStart {
  def main(args : Array[String]) : Unit = {
    val platform = JavaCL.listGPUPoweredPlatforms()(0)
    val gpu = platform.listGPUDevices(true).last
    
    val ctx = JavaCL.createContext(null,gpu)
    val queue = ctx.createDefaultQueue()
     
    while(true) { Thread.sleep(60000) }
  }
}