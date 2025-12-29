package io.github.kevvy.chat_java.redis;

import io.github.kevvy.chat_java.entity.constants.Constants;
import io.github.kevvy.chat_java.entity.dto.SysSettingDTO;
import io.github.kevvy.chat_java.entity.dto.TokenUserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisComponent {

    private final RedisUtil redisUtil;

    /**
     * 获取最后心跳
     */
    public Long getUserHeatBeat(String userID) {
        return redisUtil.get(Constants.REDIS_KEY_WS_USER_HEART_BEAT + userID) == null ? null : (Long) redisUtil.get(Constants.REDIS_KEY_WS_USER_HEART_BEAT + userID);
    }

    /**
     * 是否在线
     */
    public boolean isUserOnline(String userId) {
        return getUserHeatBeat(userId) != null;
    }

    /**
     * 把用户初始化文件，userid，用来刷新token的数据存入redis，
     * @param tokenUserInfoDto
     */
    public void saveTokenUserInfoDto(TokenUserInfoDTO tokenUserInfoDto) {
        redisUtil.set(Constants.REDIS_KEY_WS_TOKEN + tokenUserInfoDto.getToken(), tokenUserInfoDto, Constants.REDIS_TIME_1DAY);
        redisUtil.set(Constants.REDIS_KEY_WS_TOKEN_REFRESH + tokenUserInfoDto.getToken(), "1", Constants.REDIS_TIME_1DAY * 2);
        redisUtil.set(Constants.REDIS_KEY_WS_TOKEN_USERID + tokenUserInfoDto.getToken(), tokenUserInfoDto.getUserID(), Constants.REDIS_TIME_1DAY);//预留

    }

    /**
     * 根据token查询用户初始化数据
     * @param token
     */
    public TokenUserInfoDTO getTokenUserInfoDto(String token) {
        return  (TokenUserInfoDTO) redisUtil.get(Constants.REDIS_KEY_WS_TOKEN + token);
    }

    /**
     * 刷新token时间
     */
    public void tokenRefresh(String token) {
        redisUtil.expire(Constants.REDIS_KEY_WS_TOKEN, Constants.REDIS_TIME_1DAY);
        redisUtil.expire(Constants.REDIS_KEY_WS_TOKEN_REFRESH, Constants.REDIS_TIME_1DAY * 2);

    }

    /**
     * 初始化系统设置
     */
    public SysSettingDTO getSysSetting() {
        SysSettingDTO settingDto = (SysSettingDTO) redisUtil.get(Constants.REDIS_SYS_SETTING);
         settingDto = settingDto==null? new SysSettingDTO():settingDto;
         return settingDto;
    }
}
