import tensorflow as tf
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing.image import ImageDataGenerator

model = load_model("C:\\Users\\orusx\\Documents\\Bangkit\\Cap\\Program\\saved_model\\mymodel.h5")
model.summary()
validation_dir = "E:\\Document\\Covid\\COVID-19_Radiography_Dataset\\dataset\\Test\\"
validation_datagen = ImageDataGenerator(rescale=1./255)
validation_generator = validation_datagen.flow_from_directory(validation_dir,
		target_size=(299,299),
		batch_size = 25,
		class_mode ="categorical")
loss, acc = model.evaluate(validation_generator)