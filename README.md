# Bangkit Capstone Project 2021

### Lung Detector - B21-CAP0053
### Theme : Healtcare

## Introduction
In this capstone project our team created COVID-19 detection based on rontgen thorax (X ray) scan of lungs. Using android based apps, with help of machine learning for classification will be built until its achieved best accuracy (98%) for the machine learning model (http://sinta.ristekbrin.go.id/covid/penelitian/detail/106). 
Raw X ray lung data gained from a dataset that was manually labeled by the professionals (radiologist), dividing into 3 classes, normal lungs, covid-19 lungs, and pneumonia lungs.  All the data will be segmented into training data, validation data, and test data and will result in classification of a patient between identified as COVID-19 or normal. Results from the classification will be presented in android apps so that the user will be able to see the result in the pleasing UI.

## Our Teams :
- Achmad Setiawan - M00256 - Machine Learning 
- Indra Kusuma - M0040252 -  Machine Learning 
- Ilham jati - C0040254 - Cloud Computing 
- Ernestine Zefanya - C1161477 - Cloud Computing 
- Miftakhul Janah Sulastri -  A0040178 - Mobile Programming (Android) 
- Salsabila Ash Shofiyah - A0040237 - Mobile Programming (Android) 

## Dataset : 
https://www.kaggle.com/tawsifurrahman/covid19-radiography-database


## Machine Learning 
#### Preparing the data
- data from kaggle (covid radiography database) that contain 3616 covid lung images, 10200 normal lung images, 1345 pneumonia lung images, and 6012 lung opacity images
- 100 images for the validation set and 100 for the test set. So it left 1145 images for training
#### Image Normalization and Preprocessing
divided the image pixels with 255.0 so the images only contain pixels that are valued between 0-1
#### Feed Images Into Convolutional Neural Network
[Documentation](https://github.com/SalsabilaAsh/Lung-Detector-Application/blob/main/main.py)
Training and validation accuracy and loss graph
![alt text](https://github.com/SalsabilaAsh/Lung-Detector-Application/blob/main/images/model.PNG)
#### Evaluate
[Documentation](https://github.com/SalsabilaAsh/Lung-Detector-Application/blob/main/evaluate.py)
#### Converting Model
result that we get is loss = 0.1188 and accuracy = 0.9600

## Cloud Computing 
[Documentation](https://github.com/SalsabilaAsh/Lung-Detector-Application/blob/main/index.html)
[Documentation](https://github.com/SalsabilaAsh/Lung-Detector-Application/blob/main/index.js)

## Mobile Programming (Android)
[Documentation](https://github.com/SalsabilaAsh/Lung-Detector-Application/tree/master)
This android application was created based on a web application by utilizing the web view feature. This feature will display the COVID-19 classification application web page.
1. Splash Screen

![alt text](https://github.com/SalsabilaAsh/Lung-Detector-Application/blob/main/images/image1.PNG)

2. Welcome Page

![alt text](https://github.com/SalsabilaAsh/Lung-Detector-Application/blob/main/images/image2.PNG)

3. Web Application View

![alt text](https://github.com/SalsabilaAsh/Lung-Detector-Application/blob/main/images/image3.PNG)

4. Information Page

![alt text](https://github.com/SalsabilaAsh/Lung-Detector-Application/blob/main/images/image4.PNG)
