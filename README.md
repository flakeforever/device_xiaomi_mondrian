# Device tree for Poco F5 Pro | Redmi K60

## Known issues
- Device Specific Issues:
- Fingerprint works on Goodix devices (most devices) and does not work on FPC devices.
- Q: How do you identify your fingerprint device? (Goodix or FPC)
- A: Install "Device Info HW" from GooglePlay to check it.

- PS：I only have a K60 of Goodix device, so I'm temporarily unable to fix this issue.

## Other issues:
To be submitted

## Kernel (Baalam Kernel)
- https://github.com/LowTension/android_kernel_xiaomi_sm8475

## Required system patches

### Bluetooth (New codec support):
- https://github.com/flakeforever/packages_modules_Bluetooth/commit/ad0825fcf314398abf0565188fdbb644445a6f6d

### Vibrator (VibratorFeature support):
- https://github.com/flakeforever/frameworks_native/commit/84babd26aaaf108c153036e9f6bf6eee0ac7ab1d

## Prebuilt files (customized from V14.0.26.0):
- https://drive.google.com/drive/folders/15Ihtjg5cZEeSgChfvwoYBC3Kv7HRyav4?usp=sharing


Credits: 
    [affggh](https://github.com/affggh)
    [johnmart19](https://github.com/johnmart19)
    [Klozz](https://github.com/Klozz)
    [LowTension](https://github.com/LowTension)

    
    
    
