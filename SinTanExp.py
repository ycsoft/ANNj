

import matplotlib.pyplot as plt
import numpy as np
from pylab  import *
import string
from mpl_toolkits.mplot3d import Axes3D

def   Sphere(xlow,xmax):
    x = np.arange(xlow,xmax,(xmax-xlow)/100.0)
    [x,y] = np.meshgrid(x,x)
    z = x**2 + y**2
    fig = plt.figure();
    ax = fig.gca(projection='3d')
    ax.plot_surface(x,y,z)
    plt.show()

def SinCos(xmin,xmax):
    x = np.linspace(xmin,xmax,400)
    [x,y] = np.meshgrid(x,x)
    z = np.sin(x**2 + y**2)/(x**2+y**2)

    fig = plt.figure()
    ax = fig.gca(projection='3d')
    ax.plot_surface(x,y,z)
    plt.show()



def mySin(xmin,xmax):
    x = np.linspace(xmin,xmax,400)
    y = np.sin(x)
    plt.plot(x,y)
    plt.show()


def loadData(fname):
    file = open(fname)
    res = []
    while 1:
        line = file.readline()
        if not line:
            break
        line = line.strip()
        res.append(string.atof(line))
    return res


if __name__ == '__main__':
    tx = loadData("test-x.txt")
    idealy = loadData("test-ideal-y.txt")
    prey = loadData("test-py.txt")
    plt.plot(tx,idealy,'r',tx,prey,'b')
    plt.legend(["Real","Predict"]);
    plt.show()




