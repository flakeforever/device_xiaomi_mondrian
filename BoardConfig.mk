#
# Copyright (C) 2022-2023 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from xiaomi sm8450-common
include device/xiaomi/sm8450-common/BoardConfigCommon.mk

# Inherit from the proprietary version
include vendor/xiaomi/mondrian/BoardConfigVendor.mk

DEVICE_PATH := device/xiaomi/mondrian
KERNEL_PREBUILT_DIR := $(DEVICE_PATH)/kernel

# Kernel
TARGET_PREBUILT_DTB := $(KERNEL_PREBUILT_DIR)/dtbs/dtb
BOARD_PREBUILT_DTBIMAGE_DIR := $(KERNEL_PREBUILT_DIR)/dtbs

PRODUCT_COPY_FILES += $(KERNEL_PREBUILT_DIR)/dtbs/dtb:dtb.img

TARGET_NEEDS_DTBOIMAGE := false
BOARD_PREBUILT_DTBOIMAGE := $(KERNEL_PREBUILT_DIR)/dtbs/dtbo.img

TARGET_FORCE_PREBUILT_KERNEL := true
TARGET_PREBUILT_KERNEL := $(KERNEL_PREBUILT_DIR)/Image

PRODUCT_COPY_FILES += $(KERNEL_PREBUILT_DIR)/Image:kernel

# Kernel Modules
BOARD_VENDOR_RAMDISK_KERNEL_MODULES := $(wildcard $(KERNEL_PREBUILT_DIR)/vendor_ramdisk/*.ko)
BOARD_VENDOR_RAMDISK_KERNEL_MODULES_LOAD := $(strip $(shell cat $(KERNEL_PREBUILT_DIR)/vendor_ramdisk/modules.load))
BOARD_VENDOR_RAMDISK_KERNEL_MODULES_BLOCKLIST_FILE := $(KERNEL_PREBUILT_DIR)/vendor_ramdisk/modules.blocklist
BOARD_VENDOR_RAMDISK_RECOVERY_KERNEL_MODULES_LOAD := $(strip $(shell cat $(KERNEL_PREBUILT_DIR)/vendor_ramdisk/modules.load.recovery))

BOARD_VENDOR_RAMDISK_FRAGMENTS := dlkm
BOARD_VENDOR_RAMDISK_FRAGMENT.dlkm.KERNEL_MODULE_DIRS := top

BOARD_VENDOR_KERNEL_MODULES := $(wildcard $(KERNEL_PREBUILT_DIR)/vendor_dlkm/*.ko)
BOARD_VENDOR_KERNEL_MODULES_LOAD := $(strip $(shell cat $(KERNEL_PREBUILT_DIR)/vendor_dlkm/modules.load))
BOARD_VENDOR_KERNEL_MODULES_BLOCKLIST_FILE :=  $(KERNEL_PREBUILT_DIR)/vendor_dlkm/modules.blocklist

# Properties
TARGET_SYSTEM_PROP += $(DEVICE_PATH)/properties/system.prop

# Screen density
TARGET_SCREEN_DENSITY := 560

# Sensor
SOONG_CONFIG_NAMESPACES += SENSORS_XIAOMI
SOONG_CONFIG_SENSORS_XIAOMI += USES_UDFPS_SENSOR
SOONG_CONFIG_SENSORS_XIAOMI_USES_UDFPS_SENSOR := true

# Vibrator
TARGET_QTI_VIBRATOR_EFFECT_LIB := libqtivibratoreffect.xiaomi
TARGET_QTI_VIBRATOR_USE_EFFECT_STREAM := true
