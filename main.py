import numpy as np
import tensorflow as tf
from tensorflow.keras.preprocessing.image import ImageDataGenerator
import matplotlib.pyplot as plt
import os

train_dir = "E:\\Document\\Covid\\COVID-19_Radiography_Dataset\\dataset\\Train\\"
validation_dir = "E:\\Document\\Covid\\COVID-19_Radiography_Dataset\\dataset\\Validation\\"

train_datagen = ImageDataGenerator(rescale=1./255)
validation_datagen = ImageDataGenerator(rescale=1./255)

train_generator = train_datagen.flow_from_directory(train_dir,
		target_size=(299,299),
		batch_size = 25,
		class_mode ="categorical")
validation_generator = validation_datagen.flow_from_directory(validation_dir,
		target_size=(299,299),
		batch_size = 25,
		class_mode ="categorical")

model = tf.keras.Sequential([
		tf.keras.layers.Conv2D(8, (3,3), padding="same", input_shape=(299,299,3), activation="relu"),
		tf.keras.layers.AveragePooling2D(2,2),
		tf.keras.layers.Conv2D(16, (3,3), activation="relu"),
		tf.keras.layers.AveragePooling2D(2,2),
		tf.keras.layers.Flatten(),
		tf.keras.layers.Dense(128, activation="relu"),
		tf.keras.layers.Dense(64, activation="relu"),
		tf.keras.layers.Dense(32, activation="relu"),
		tf.keras.layers.Dense(3, activation="softmax"),
		])

model.compile(loss='categorical_crossentropy', optimizer="adam", metrics=['accuracy'])
history = model.fit_generator(train_generator, epochs=10, validation_data=(validation_generator))

filePath = 'C:\\Users\\orusx\\Documents\\Bangkit\\Cap\\Program\\saved_model\\'
fileName = 'mymodel2.h5'
model.save(os.path.join(filePath, fileName))

acc = history.history['accuracy']
val_acc = history.history['val_accuracy']
loss = history.history['loss']
val_loss = history.history['val_loss']

epochs = range(len(acc))

plt.plot(epochs, acc, 'b', color='red', label='Training accuracy')
plt.plot(epochs, val_acc, 'b', label='Validation accuracy')
plt.title('Training and validation accuracy')
plt.legend()

plt.figure()

plt.plot(epochs, loss, 'b', color='red', label='Training Loss')
plt.plot(epochs, val_loss, 'b', label='Validation Loss')
plt.title('Training and validation loss')
plt.legend()

plt.show()