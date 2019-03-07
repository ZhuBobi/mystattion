package com.zhu.bobi.commonutil;

import java.io.*;

public class ObjectClone {

  private static ObjectClone objectClone;

  public static synchronized ObjectClone getInstance() {
    if (null == objectClone) {
      synchronized (ObjectClone.class) {
        if (null == objectClone) {
          objectClone = new ObjectClone();
        }
      }
    }
    return objectClone;
  }

  public <T> T clone(T object) {
    T outer = null;
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      oos.writeObject(object);
      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
      ObjectInputStream ois = new ObjectInputStream(bais);
      outer = (T) ois.readObject();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return outer;
  }
}