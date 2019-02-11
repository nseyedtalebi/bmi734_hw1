(ns bmi734-hw1.problem-1
  (:gen-class)
  (:import (org.opencv.core Mat Core CvType)
           (org.opencv.imgproc Imgproc)
           (org.opencv.imgcodecs Imgcodecs)))

(defn write-cleaned-image []
                               (let [embryos (Imgcodecs/imread "resources/embryos.jpg")
                                     embryos_cleaned (Mat.)]
                                (Imgproc/medianBlur embryos embryos_cleaned 3)
                                (Imgcodecs/imwrite "embryos_cleaned.jpg" embryos_cleaned)
                               )
  )
(defn write-segmented-image []
                                 (def mri-brain (Imgcodecs/imread "resources/mri_brain.jpg"))
                                   (def mri-brain-segmented (Mat.))
                                   (Imgproc/Canny mri-brain mri-brain-segmented 75 200)
                                 (let [
                                     imsize (.size mri-brain-segmented)
                                     zeros  (Mat/zeros imsize CvType/CV_8U)
                                     overlay (Mat.)
                                     output (Mat.)
                                     ]
                                 (Core/merge [zeros zeros mri-brain-segmented] overlay)
                                 (Core/scaleAdd overlay 255 mri-brain output)
                                 (Imgcodecs/imwrite "mri_brain_segd.jpg" output)
                                 )
                               )
  (defn generate-test-images [input-filename output-filename]
    (let [input-image (Imgcodecs/imread input-filename)
          ;steps (range 4096 65536 (/ 65536 16))
          steps (range 70 80 1)
          neg (Mat.)
          ;steps (range 100 200 5)
          ;steps (range 200 300 5)
          ;steps (range 300 400 5)
          output-image (Mat.)]
      (doseq [step steps]
        (println step)
        (Core/bitwise_not input-image neg)
        (Imgproc/Canny neg output-image 200 step)
        (Imgcodecs/imwrite (str "tmp/" output-filename step ".jpg") output-image)
        )
      )
    )

(defn -main []
  (write-cleaned-image)
  (write-segmented-image)
  )
