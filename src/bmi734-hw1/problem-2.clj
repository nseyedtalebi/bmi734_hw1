(ns bmi734-hw1.problem-2
(:gen-class)
  (:import (org.opencv.core Mat Core)
           (org.opencv.imgcodecs Imgcodecs)
           (org.opencv.imgproc Imgproc)
          ))
(defn get-segmented-image [] (
                               (def mri-brain (Imgcodecs/imread "resources/mri_brain.jpg"))
                               (def mri-brain-segmented (Mat.))
                               (Imgproc/Canny mri-brain mri-brain-segmented 75 200)
                               (Imgcodecs/imwrite "mri-brain-segd.jpg" mri-brain-segmented)
                               ))
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

;TODO create new 3-channel image by using colorspace conversion to RBG
;After that, set blue and green channels to zero
;Then add that new image to the input to overlay
(def mri-brain (Imgcodecs/imread "resources/mri_brain.jpg"))
(def mri-brain-segmented (Mat.))
(Imgproc/Canny mri-brain mri-brain-segmented 75 200)
(def mri-brain-seg-c (Mat.))
(Imgproc/cvtColor mri-brain-segmented mri-brain-seg-c Imgproc/COLOR_GRAY2BGR)