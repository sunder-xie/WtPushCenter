package com.woting.push.config;

import java.util.Map;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.spiritdata.framework.jsonconf.JsonConfig;
import com.woting.audioSNS.calling.CallingConfig;
import com.woting.audioSNS.ctrlremsg.AffirmCtlConfig;
import com.woting.audioSNS.notify.NotifyMessageConfig;
import com.woting.audioSNS.sync.SyncMessageConfig;
import com.woting.push.core.SocketHandleConfig;
import com.woting.audioSNS.intercom.IntercomConfig;
import com.woting.audioSNS.mediaflow.MediaConfig;

public abstract class ConfigLoadUtils {
    private final static FelEngine fel=new FelEngineImpl();

    public static PushConfig getPushConfig(JsonConfig jc) {
        PushConfig pc=new PushConfig();
        try {
            pc.set_SocketServerType(jc.getInt("pushserver.socketType"));
        } catch(Exception e) {}
        try {
            pc.set_ServerType(jc.getString("serverIdentify.serverType"));
        } catch(Exception e) {}
        try {
            pc.set_ServerName(jc.getString("serverIdentify.serverName"));
        } catch(Exception e) {}
        try {
            pc.set_ControlTcpPort((int)fel.eval(jc.getString("pushserver.ctlTcpPort")));
        } catch(Exception e) {}
        try {
            pc.set_CleanInterval((int)fel.eval(jc.getString("pushserver.cleanInterval")));
        } catch(Exception e) {}
        return pc;
    }

    public static SocketHandleConfig getSocketHandleConfig(JsonConfig jc) {
        SocketHandleConfig shc=new SocketHandleConfig();
        try {
            shc.set_ExpireTime((int)fel.eval(jc.getString("socketmonitor.expireTime")));
        } catch(Exception e) {}
        try {
            shc.set_MonitorDelay((int)fel.eval(jc.getString("socketmonitor.monitorDelay")));
        } catch(Exception e) {}
        try {
            shc.set_TryDestoryAllCount((int)fel.eval(jc.getString("socketmonitor.tryDestoryAllCount")));
        } catch(Exception e) {}
        try {
            shc.set_NotifyInternal((int)fel.eval(jc.getString("socketmonitor.notifyInternal")));
        } catch(Exception e) {}
        try {
            shc.set_Err_ContinueCount((int)fel.eval(jc.getString("socketmonitor.errContinueCount")));
        } catch(Exception e) {}
        try {
            shc.set_Err_SumCount((int)fel.eval(jc.getString("socketmonitor.errSumCount")));
        } catch(Exception e) {}
        try {
            shc.set_LogPath(jc.getString("socketenviron.logPath"));
        } catch(Exception e) {}
        return shc;
    }

    public static IntercomConfig getIntercomConfig(JsonConfig jc) {
        IntercomConfig ic=new IntercomConfig();
        try {
            ic.set_DealThreadCount((int)fel.eval(jc.getString("intercom.dealThread")));
        } catch(Exception e) {}
        try {
            ic.set_CtrAffirmType((int)fel.eval(jc.getString("intercom.ctrAffirmType")));
        } catch(Exception e) {}
        try {
            ic.set_ExpireTime((int)fel.eval(jc.getString("intercom.expireTime")));
        } catch(Exception e) {}
        try {
            ic.set_ExpireSpeakerTime((int)fel.eval(jc.getString("intercom.expireSpeakerTime")));
        } catch(Exception e) {}
        return ic;
    }

    public static CallingConfig getCallingConfig(JsonConfig jc) {
        CallingConfig cc=new CallingConfig();
        try {
            cc.set_DealThreadCount((int)fel.eval(jc.getString("calling.dealThread")));
        } catch(Exception e) {}
        try {
            cc.set_CtrAffirmType((int)fel.eval(jc.getString("calling.ctrAffirmType")));
        } catch(Exception e) {}
        try {
            cc.set_ExpireOnline((int)fel.eval(jc.getString("calling.expireOnline")));
        } catch(Exception e) {}
        try {
            cc.set_ExpireAck((int)fel.eval(jc.getString("calling.expireAck")));
        } catch(Exception e) {e.printStackTrace();}
        try {
            cc.set_ExpireTime((int)fel.eval(jc.getString("calling.expireTime")));
        } catch(Exception e) {}
        try {
            cc.set_ExpireSpeakerTime((int)fel.eval(jc.getString("calling.expireSpeakerTime")));
        } catch(Exception e) {}
        try {
            cc.set_CleanInternal((int)fel.eval(jc.getString("calling.clean.cleanInternal")));
        } catch(Exception e) {}
        try {
            cc.set_CleanDataExpire((int)fel.eval(jc.getString("calling.clean.dataExpire")));
        } catch(Exception e) {}
        return cc;
    }

    public static NotifyMessageConfig getNotifyMessageConfig(JsonConfig jc) {
        NotifyMessageConfig nmc=new NotifyMessageConfig();
        try {
            nmc.set_DealThreadCount((int)fel.eval(jc.getString("notify.dealThread")));
        } catch(Exception e) {}
        try {
            nmc.set_Delay((int)fel.eval(jc.getString("notify.delay")));
        } catch(Exception e) {}
        try {
            nmc.set_ExpLmtPerDevice((int)fel.eval(jc.getString("notify.expLmtPerDevice")));
        } catch(Exception e) {}
        try {
            nmc.set_ExpLmtPerUser((int)fel.eval(jc.getString("notify.expLmtPerUser")));
        } catch(Exception e) {}
        try {
            nmc.set_ExpireTime((int)fel.eval(jc.getString("notify.expireTime")));
        } catch(Exception e) {}
        return nmc;
    }

    public static SyncMessageConfig getSyncMessageConfig(JsonConfig jc) {
        SyncMessageConfig smc=new SyncMessageConfig();
        try {
            smc.set_DealThreadCount((int)fel.eval(jc.getString("notify.dealThread")));
        } catch(Exception e) {}
        return smc;
    }

    public static AffirmCtlConfig getAffirmCtlConfig(JsonConfig jc) {
        AffirmCtlConfig acc=new AffirmCtlConfig();
        try {
            acc.set_DealThreadCount((int)fel.eval(jc.getString("controlAffirm.dealThread")));
        } catch(Exception e) {}
        try {
            acc.set_N_Type((int)fel.eval(jc.getString("controlAffirm.normalMsg.type")));
        } catch(Exception e) {}
        try {
            acc.set_N_ExpireTime((int)fel.eval(jc.getString("controlAffirm.normalMsg.expireTime")));
        } catch(Exception e) {}
        try {
            acc.set_N_ExpireLimit((int)fel.eval(jc.getString("controlAffirm.normalMsg.expireLimit")));
        } catch(Exception e) {}
        try {
            acc.set_N_InternalResend((int)fel.eval(jc.getString("controlAffirm.normalMsg.internalResend")));
        } catch(Exception e) {}
        try {
            acc.set_M_Type((int)fel.eval(jc.getString("controlAffirm.mediaMsg.type")));
        } catch(Exception e) {}
        try {
            acc.set_M_ExpireTime((int)fel.eval(jc.getString("controlAffirm.mediaMsg.expireTime")));
        } catch(Exception e) {}
        try {
            acc.set_M_ExpireLimit((int)fel.eval(jc.getString("controlAffirm.mediaMsg.expireLimit")));
        } catch(Exception e) {}
        try {
            acc.set_M_InternalResend((int)fel.eval(jc.getString("controlAffirm.mediaMsg.internalResend")));
        } catch(Exception e) {}
        try {
            acc.set_CleanInternal((int)fel.eval(jc.getString("controlAffirm.cleanInternal")));
        } catch(Exception e) {}
        return acc;
    }

    public static MediaConfig getMediaConfig(JsonConfig jc) {
        MediaConfig mc=new MediaConfig();
        try {
            mc.set_CtrAffirmType((int)fel.eval(jc.getString("mediaMessage.ctrAffirmType")));
        } catch(Exception e) {}
        try {
            mc.set_AudioPackT((int)fel.eval(jc.getString("mediaMessage.audio.packT")));
        } catch(Exception e) {}
        try {
            mc.set_AudioPackExpiredTime((int)fel.eval(jc.getString("mediaMessage.audio.packExpired")));
        } catch(Exception e) {}
        try {
            mc.set_AudioExpiredType((int)fel.eval(jc.getString("mediaMessage.audio.expired.type")));
        } catch(Exception e) {}
        try {
            mc.set_AudioExpiredTime((int)fel.eval(jc.getString("mediaMessage.audio.expired.expiredTime")));
        } catch(Exception e) {}
        try {
            mc.set_AudioExpiredTNum((int)fel.eval(jc.getString("mediaMessage.audio.expired.expiredTNum")));
        } catch(Exception e) {}
        try {
            mc.set_VedioPackT((int)fel.eval(jc.getString("mediaMessage.vedio.packT")));
        } catch(Exception e) {}
        try {
            mc.set_VedioPackExpiredTime((int)fel.eval(jc.getString("mediaMessage.vedio.packExpired")));
        } catch(Exception e) {}
        try {
            mc.set_VedioExpiredType((int)fel.eval(jc.getString("mediaMessage.vedio.expired.type")));
        } catch(Exception e) {}
        try {
            mc.set_VedioExpiredTime((int)fel.eval(jc.getString("mediaMessage.vedio.expired.expiredTime")));
        } catch(Exception e) {}
        try {
            mc.set_VedioExpiredTNum((int)fel.eval(jc.getString("mediaMessage.vedio.expired.expiredTNum")));
        } catch(Exception e) {}
        return mc;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static UrlConvertConfig getUrlConvertConfig(JsonConfig jc) {
        UrlConvertConfig ucc=new UrlConvertConfig();
        try {
            Object _ucc=jc.getValue("urlReplaceRule");
            ucc.setConvertRules((Map)_ucc);
        } catch(Exception e) {e.printStackTrace();}
        return ucc;
    }
}