(ns bmi734-hw1.OpenCVHelpers
  (:gen-class)
  (:import
           (org.opencv.highgui HighGui)
           (javax.swing ImageIcon JLabel JFrame)
           (java.awt FlowLayout)))
(defn display-mat [mat]
  ;OpenCV's HighGUI doesn't work very well so I adapted this nice example:
  ;http://answers.opencv.org/question/8119/how-to-display-image-on-java-release/
  (let [frame (HighGui/createJFrame (:name (meta mat)) HighGui/WINDOW_AUTOSIZE)
        buffered-img (HighGui/toBufferedImage mat)
        icon (ImageIcon. buffered-img)
        h (+ 50 (.getHeight buffered-img nil))
        w (+ 50 (.getWidth buffered-img nil))
        lbl (JLabel.)
        ]
    (.setLayout frame (FlowLayout.))
    (.setSize frame w h)
    (.setIcon lbl icon)
    (.add frame lbl)
    (.setVisible frame true)
    (.setDefaultCloseOperation frame JFrame/DISPOSE_ON_CLOSE)
    )
  )