package org.example.practicejava.deepcopy.java8feature;

/**
 * @author wangyulin
 * @date 2020/4/13
 */
public class GroupBrandCateBO {
    private String version;
    private String groupCode;
    private String brand;

    public GroupBrandCateBO(String version, String groupCode, String brand) {
        this.version = version;
        this.groupCode = groupCode;
        this.brand = brand;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
