package org.example.practice.practicecode.javalang.objectcopy.deepserializable;

/**
 * 利用序列化，进行深拷贝
 *
 * 实现Serializable接口，让对象支持序列化
 */
public class Address extends DeepClone {
    private static final long serialVersionUID = 1L;
    private String province;
    private String street;

    public Address(String province, String street) {
        this.province = province;
        this.street = street;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" + "province='" + province + '\'' + ", street='" + street + '\'' + '}';
    }
}
