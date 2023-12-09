#
# Copyright (C) 2023 The Android Open Source Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)

# Inherit some common DerpFest stuff.
$(call inherit-product, vendor/aosp/config/common_full_phone.mk)

# Inherit from mondrian device.
$(call inherit-product, device/xiaomi/mondrian/device.mk)

# Rom Stuff
EXTRA_UDFPS_ANIMATIONS := true

# Boot animation
TARGET_BOOT_ANIMATION_RES := 1440
TARGET_SCREEN_HEIGHT := 3200
TARGET_SCREEN_WIDTH := 1440
#TARGET_SCREEN_DENSITY := 560
# TARGET_SCREEN_HEIGHT := 2400
# TARGET_SCREEN_WIDTH := 1080
# TARGET_SCREEN_DENSITY := 420

TARGET_GAPPS_ARCH := arm64
TARGET_FACE_UNLOCK_SUPPORTED := true
TARGET_USES_BLUR := true
TARGET_SUPPORTS_QUICK_TAP := true

## Device identifier
PRODUCT_BRAND := Redmi
PRODUCT_DEVICE := mondrian
PRODUCT_NAME := aosp_mondrian
PRODUCT_MANUFACTURER := Xiaomi

# PRODUCT_BUILD_PROP_OVERRIDES += \
#     PRIVATE_BUILD_DESC="mondrian_user 13 SKQ1.220303.001 V14.0.26.0.TMNCNXM release-keys" \
#     TARGET_DEVICE=$(PRODUCT_SYSTEM_DEVICE) \
#     TARGET_PRODUCT=$(PRODUCT_SYSTEM_NAME)

BUILD_FINGERPRINT := Redmi/mondrian/mondrian:13/SKQ1.220303.001/V14.0.26.0.TMNCNXM:user/release-keys

# GMS
PRODUCT_GMS_CLIENTID_BASE := android-xiaomi
