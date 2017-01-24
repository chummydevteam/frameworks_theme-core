package com.slimroms.themecore;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Overlay implements Parcelable {
    public final List<OverlayFlavor> flavors = new ArrayList<>();

    public String overlayName;
    public String targetPackage;
    public boolean isOverlayInstalled;
    public boolean isTargetPackageInstalled;
    public boolean checked;

    public Overlay(String overlayName, String targetPackage, boolean isTargetPackageInstalled) {
        this.overlayName = overlayName;
        this.targetPackage = targetPackage;
        this.isOverlayInstalled = false;
        this.isTargetPackageInstalled = isTargetPackageInstalled;
        this.checked = false;
    }

    private Overlay(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(overlayName);
        parcel.writeString(targetPackage);
        parcel.writeInt(isOverlayInstalled ? 1 : 0);
        parcel.writeInt(isTargetPackageInstalled ? 1 : 0);
        parcel.writeInt(checked ? 1 : 0);
        parcel.writeTypedList(flavors);
    }

    public void readFromParcel(Parcel in) {
        overlayName = in.readString();
        targetPackage = in.readString();
        isOverlayInstalled = in.readInt() == 1;
        isTargetPackageInstalled = in.readInt() == 1;
        checked = in.readInt() == 1;
        in.readTypedList(flavors, OverlayFlavor.CREATOR);
    }

    public void clearSelectedFlavors() {
        for (OverlayFlavor flavor : flavors)
            flavor.selected = false;
    }

    public static final Creator<Overlay> CREATOR = new Creator<Overlay>() {
        @Override
        public Overlay createFromParcel(Parcel parcel) {
            return new Overlay(parcel);
        }

        @Override
        public Overlay[] newArray(int i) {
            return new Overlay[i];
        }
    };
}