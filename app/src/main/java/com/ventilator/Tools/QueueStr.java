package com.ventilator.Tools;


public class QueueStr<E> {
    private Object[] data = null;// 队列
    private int front;// 队列头，允许删除

    public int getRear() {
        return rear;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    private int rear;// 队列尾，允许插入

    public QueueStr() {
        this(10);// 默认队列的大小为10
    }

    public QueueStr(int initialSize) {
        data = new Object[initialSize];
        front = rear = 0;
    }

    // 入列一个元素
    public void offer(E e) {
        rear++;
        if(rear<data.length)
        {
            data[rear] = e;
            }
        else{
            poll();

            data[rear] = e;
        }


    }

    // 返回队首元素，但不删除
    public E peek() {
        return (E) data[front];
    }

    // 出队排在最前面的一个元素
    public E poll() {
        E value = (E) data[front];// 保留队列的front端的元素的值
      for(int i=0;i<data.length-1;i++)
      {
          data[i]=data[i+1];

      }
      if (rear>1)
      {
        rear--;}
        return value;
    }

    //取出全部字符串
    public String getstring()
    {
        String res="";
        for (int i=0;i<data.length;i++)
        {
            if(data[i]!=null){
           res+= data[i].toString();
           res+="\n";}
        }
        return  res;
    }
}
