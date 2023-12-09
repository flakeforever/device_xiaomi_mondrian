
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

ALL_TOOLS := \
    capture.sh \
    playback.sh \
    setup_backmic2headphone.sh \
    setup_frontmic2headphone.sh \
    setup_headsetmic2headphone.sh \
	setup_mainmic2headphone.sh \
	setup_topmic2headphone.sh \
	teardown_loopback.sh \
	typec_capture_headset.sh \
	typec_playback_headset.sh

LOCAL_MODULE       := audio-factory-test
LOCAL_MODULE_TAGS  := optional
LOCAL_MODULE_CLASS := EXECUTABLES
LOCAL_SRC_FILES    := bin/audio-factory-test
LOCAL_MODULE_PATH  := $(TARGET_OUT_VENDOR_EXECUTABLES)

LOCAL_SHARED_LIBRARIES := \
        libcutils \
        liblog \
        libtinyalsa \
        libhardware_legacy

LOCAL_POST_INSTALL_CMD := $(hide) $(foreach t,$(ALL_TOOLS),ln -sf audio-factory-test $(TARGET_OUT_VENDOR_EXECUTABLES)/$(t);)
include $(BUILD_PREBUILT)