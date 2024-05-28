import numpy as np
import matplotlib.pyplot as plt
size =30
x_value = np.random.rand(size)
y_valud = np.random.rand(size)
sizeAry = (50 * np.random.rand(size))**2
colorAry = np.random.rand(size)

plt.scatter(x_value,y_valud,s=sizeAry,alpha=0.5,cmap='spring')
plt.colorbar()
plt.show()