(ns bmi734-hw1.problem-1
  (:gen-class)
  (:import (org.opencv.core Mat)
           (org.opencv.imgproc Imgproc)
           (org.opencv.imgcodecs Imgcodecs)))
(defn -main [] (
                 (def embryos (Imgcodecs/imread "resources/embryos.jpg"))
                 (def embryos_cleaned (Mat.))
                 (Imgproc/medianBlur embryos embryos_cleaned 3)
                 (Imgcodecs/imwrite "embryos_cleaned" embryos_cleaned)
                 )
  )
