# How to build

## Get device files
    cd peplus/device
    mkdir xiaomi
    cd xiaomi
    git clone https://github.com/flakeforever/device_xiaomi_mondrian.git
    git clone https://github.com/flakeforever/device_xiaomi_sm8450-common.git

## Get kernel file
    cd peplus/kernel
    mkdir xiaomi
    cd xiaomi
    git clone https://github.com/cupid-development/android_kernel_xiaomi_sm8450.git -b mondrian sm8450

## Get vendor files
    cd peplus/vendor
    download https://drive.google.com/open?id=1-R5OLLWLvmLrI6iCBNBoMh4wPJJIEbz1&usp=drive_fs
    unzip file

## Required system patches
    cd peplus/
    device/xiaomi/mondrian/apply-patches.sh

## Build project
    start to build
Credits: 
    [affggh](https://github.com/affggh)
    [ArianK16a](https://github.com/ArianK16a)
    [johnmart19](https://github.com/johnmart19)
    [Klozz](https://github.com/Klozz)
