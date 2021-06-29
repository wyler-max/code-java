package org.example.practicecode.designpattern.structureType.adapterUSB;

/**
 * 接口适配器，使用抽象类实现接口，并为该接口中每个方法提供一个默认实现
 */
public abstract class AdapterUSB2VGA3_Abstract implements VGA {
    protected USB usb = new USBImpl();

    @Override
    public String showScreenData(String data) {
        System.out.println("===== 对象适配器 =====");
        // usb 输出
        String usbOutData = usb.showData(data);
        // 适配器工作
        String adapterOutData = adapterData(usbOutData);
        // vga 输入
        VGA vga = new VGAImpl();
        return vga.showScreenData(adapterOutData);
    }

    @Override
    public void otherA() {
        // override this method
    }

    @Override
    public void otherB() {
        // override this method
    }

    private String adapterData(String data) {
        System.out.println("开始适配转码...");
        StringBuilder stringBuilder = new StringBuilder(data);
        return stringBuilder.reverse().toString();
    }
}
