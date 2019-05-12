package com.example.opencvtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.JavaCameraView
import org.opencv.android.OpenCVLoader
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class MainActivity : AppCompatActivity(), CameraBridgeViewBase.CvCameraViewListener {


    var javaCameraView: JavaCameraView? = null
    var baseLoaderCallback: BaseLoaderCallback
    var matRgb: Mat? = null
    var matGrey: Mat? = null
    var matCanny: Mat? = null

    companion object{
        val TAG = "MainActivity"
    }

    init {
        baseLoaderCallback = object: BaseLoaderCallback(this){
            override fun onManagerConnected(status: Int) {
                when(status){
                    SUCCESS->{
                        javaCameraView?.enableView()
                        return
                    }
                    else->{
                        super.onManagerConnected(status)
                    }
                }

            }
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        javaCameraView = findViewById(R.id.java_camera_view)
        javaCameraView?.visibility = SurfaceView.VISIBLE

        javaCameraView?.setCvCameraViewListener(this)

    }

    override fun onResume() {
        super.onResume()
        if (OpenCVLoader.initDebug()){
            Log.d(TAG,"Open CV Loaded Success")
            baseLoaderCallback.onManagerConnected(BaseLoaderCallback.SUCCESS)
        }else{
            Log.d(TAG,"Open CV not loaded")
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_13, this, baseLoaderCallback)
        }
    }

    override fun onPause() {
        super.onPause()
        if (javaCameraView != null){
            javaCameraView?.disableView()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (javaCameraView != null){
            javaCameraView?.disableView()
        }
    }

    override fun onCameraViewStarted(width: Int, height: Int) {
        matRgb = Mat(width,height, CvType.CV_8UC4)
        matGrey = Mat(width,height, CvType.CV_8UC1)
        matCanny = Mat(width,height, CvType.CV_8UC1)
    }

    override fun onCameraViewStopped() {
        matRgb?.release()
        matGrey?.release()
        matCanny?.release()
    }

    override fun onCameraFrame(inputFrame: Mat?): Mat? {
        matRgb = inputFrame
        Imgproc.cvtColor(matRgb,matGrey, Imgproc.COLOR_BGR2GRAY)
        Imgproc.Canny(matGrey, matCanny, 50.0, 150.0)
        return matCanny
    }


}
