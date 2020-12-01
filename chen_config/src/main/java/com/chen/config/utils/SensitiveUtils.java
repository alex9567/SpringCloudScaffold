package com.chen.config.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对敏感信息按 进行部分隐藏的工具类。
 * 功能说明：把敏感数据转换成:部分原数据 + *** 的形式，如 130****5634 <br/>
 * <br/>
 * <strong> 当<code>hideFlag == true </code>时才屏蔽，项目测试阶段可以关闭开关，测试结束后再开启。</strong> <br/>
 * <br/>
 * 示例：
 * <pre>
 * 银行卡号：
 * SensitiveUtils.bankCardNohide("612526198319131434") = "612526********1434"
 * <br/>
 * 身份证号：
 * SensitiveUtils.idCardNohide("362397201201012384") = "36239***********84"
 * <br/>
 * 邮箱：
 * SensitiveUtils.emailHide("yaaachaa@163.com") = "yaa***@163.com"
 * SensitiveUtils.emailHideSMS("yaaachaa@163.com") = "yaa*@163.com"
 * SensitiveUtils.emailHideSMS("yaaachaa@netvigator.com") = "yaa*@netviga*"
 * <br/>
 * 电话号码（手机或固定电话）--网站以及客户端
 * SensitiveUtils.phoneOrTelNoHide("15087653459") = "150****3459"
 * SensitiveUtils.phoneOrTelNoHide("0796-1234567") = "0796-***4567"
 * SensitiveUtils.phoneOrTelNoHide("01036852045") = "0103***2045"
 *
 * 手机号码通用隐藏规则（包括港澳台地区），隐藏中间四位--网站以及客户端
 * SensitiveUtils.cellphoneHide("13071835358") = 130****5358
 * SensitiveUtils.cellphoneHide("3071835358") = 07****358
 * SensitiveUtils.cellphoneHide("071835358") = 71****58
 * SensitiveUtils.cellphoneHide("835358") = 8****8
 * 适用于短信中
 * SensitiveUtils.cellphoneHideSMS("13071835358") = 130*5358
 * SensitiveUtils.cellphoneHideSMS("3071835358") = 07*358
 * SensitiveUtils.cellphoneHideSMS("071835358") = 71*58
 * SensitiveUtils.cellphoneHideSMS("835358") = 8*8
 * <br/>
 * zhifubao登录名：
 * SensitiveUtils.alipayLogonIdHide("15087653459") = "150****3459"
 * SensitiveUtils.alipayLogonIdHide("yaaachaa@163.com") = "yaa***@163.com"
 * <br/>
 * 非上述固定格式敏感信息的屏蔽方法：
 * SensitiveUtils.defualtHide("ttt") = "t*t"
 * <br/>
 * 自定义屏蔽规则展示：
 * SensitiveUtils.customizeHide("13568794561",3,4,4) = "135****4561"
 * <br/>
 * 对起始标签和结尾标签中间的内容按指定的敏感数据类型进行部分隐藏:
 * SensitiveUtils.filterHide(final String sourceStr,final String tagBegin, final String tagEnd,final int sensitiveDataType )
 * sourceStr为：
 *
 * <pre>
 *  &lt;Party id=&quot;part_2&quot;&gt;
 *   &lt;FullName&gt;yimin.jiang&lt;/FullName&gt;
 *   &lt;GovtIDTC tc=&quot;802&quot;&gt;&lt;/GovtIDTC&gt;
 *   &lt;GovtID&gt;432926198110191188&lt;/GovtID&gt;
 *   &lt;GovtTermDate&gt;2011-06-07&lt;/GovtTermDate&gt;
 *  &lt;/Party&gt;
 *  <br/>
 * <code>tagBegin: &lt;GovtID&gt;</code>
 * <code>tagEnd: &lt;/GovtID&gt;</code>
 * <code>sensitiveInfoType: SensitiveUtils.IDCARDNO_DATA</code>
 * </pre>
 * <p>
 * 方法返回值:
 *
 * <pre>
 *  &lt;Party id=&quot;part_2&quot;&gt;
 *   &lt;FullName&gt;yimin.jiang&lt;/FullName&gt;
 *   &lt;GovtIDTC tc=&quot;802&quot;&gt;&lt;/GovtIDTC&gt;
 *   &lt;GovtID&gt;432926********1188&lt;/GovtID&gt;
 *   &lt;GovtTermDate&gt;2011-06-07&lt;/GovtTermDate&gt;
 *  &lt;/Party&gt;
 * </pre>
 */
public class SensitiveUtils {
    /**
     * 是否进行敏感数据屏蔽的开关
     */
    private static boolean hideFlag = true;

    // 敏感信息类型标识常量
    /**
     * 银行卡号数据类型标识，可用于SensitiveUtils.filterHide方法的sensitiveDataType参数。
     */
    public static final int BANKCARDNO_DATA = 0;
    /**
     * 身份证号数据类型标识，可用于SensitiveUtils.filterHide方法的sensitiveDataType参数。
     */
    public static final int IDCARDNO_DATA = 1;
    /**
     * 电话号码数据类型标识，可用于SensitiveUtils.filterHide方法的sensitiveDataType参数。
     */
    public static final int PHONENO_DATA = 2;
    /**
     * EMAIL数据类型标识，可用于SensitiveUtils.filterHide方法的sensitiveDataType参数。
     */
    public static final int EMAIL_DATA = 3;

    /**
     * 大陆身份证号正则表达式
     */
    private static final String ID_CARD_REGEXP = "[0-9]{15}|[0-9]{18}|[0-9]{14}X|[0-9]{17}X";
    /**
     * 银行卡号正则表达式
     */
    private static final String BANK_CARD_REGEXP = "[0-9]{13,19}";
    /**
     * 手机或者固定电话正则表达式
     */
    private static final String PHONE_TEL_REGEXP = "[0-9]{3,4}[-]?[0-9]{7,8}";
    /**
     * 大陆身份证号匹配模式
     */
    private static final Pattern ID_CARD_PATTERN = Pattern.compile(ID_CARD_REGEXP);
    /**
     * 银行卡号匹配模式
     */
    private static final Pattern BANK_CARD_PATTERN = Pattern.compile(BANK_CARD_REGEXP);
    /**
     * 手机或者固定电话匹配模式
     */
    private static final Pattern PHONE_TEL_PATTERN = Pattern.compile(PHONE_TEL_REGEXP);

    /**
     * 构造函数
     *
     * @param hideFlag 是否需要进行屏蔽的开关；<code>ture</code>表示打开。
     */
    public SensitiveUtils(final boolean hideFlag) {
        SensitiveUtils.setHideFlag(hideFlag);
    }

    /**
     * 构造函数，默认开启屏蔽开关
     */
    public SensitiveUtils() {
        SensitiveUtils.setHideFlag(true);
    }

    /**
     * 通过是否含有@符号，简单判断是否是Email地址。
     *
     * @param email
     * @return
     */
    private static boolean isEmail(final String email) {
        return email.indexOf('@') > 0;
    }

    /**
     * 对zhifubao登陆帐号进行部分隐藏处理：如果是手机号，显示前3和后4位；如果是email，只显示用户名的前3位+*+@域名；如果都不是，显示前3和后4位
     * （不足7个字符的，全部显示，超过7个字符的，中间的用'*'代替）。<br/>
     *
     * @param logonId 待部分隐藏处理的zhifubao登陆帐号，可能是手机号或email。
     * @return 如果hideFlag为true，返回按规范进行部分隐藏后的登录号。
     */
    public static String alipayLogonIdHide(final String logonId) {
        if (!needHide()) {
            return logonId;
        }

        if (isBlank(logonId)) {
            return logonId;
        }

        if (isEmail(logonId)) {
            return emailHide(logonId);
        } else {
            return cellphoneHide(logonId);
        }
    }

    /**
     * 对zhifubao登陆帐号进行部分隐藏处理，适用于短信。<br/>
     *
     * @param logonId 待部分隐藏处理的zhifubao登陆帐号，可能是手机号或email。
     * @return 如果hideFlag为true，返回按规范进行部分隐藏后的登录号。
     */
    public static String alipayLogonIdHideSMS(final String logonId) {
        if (!needHide()) {
            return logonId;
        }

        if (isBlank(logonId)) {
            return logonId;
        }

        if (isEmail(logonId)) {
            return emailHideSMS(logonId);
        } else {
            return cellphoneHideSMS(logonId);
        }
    }

    /**
     * 对大陆身份证号进行部分隐藏处理，只显示前5位和后2位，其他用*代替。<br/>
     * 如果doValidate为true且传入的数据不是合法的大陆身份证号，将按敏感信息缺省隐藏方式处理，显示前1/3和后1/3，其他用*代替。<br/>
     *
     * @param idCardNo   待部分隐藏处理的身份证号。
     * @param doValidate 是否做身份证号合法性校验。警告：做校验会进行正则匹配，性能上比不做校验的方法略有损耗。
     * @return 如果hideFlag为true，返回符合《zhifubao会员信息展示规范》的身份证号部分展示字符串；否则返回原数据。
     */
    public static String idCardNoHide(final String idCardNo, final boolean doValidate) {
        if (!needHide()) {
            return idCardNo;
        }

        if (isBlank(idCardNo)) {
            return idCardNo;
        }

        if (doValidate) {
            if (!isIdCardNo(idCardNo)) {
                // 不是大陆身份证号，按缺省的隐藏显示方法。
                return defaultHide(idCardNo);
            }
        }
        return customizeHide(idCardNo, 5, 2, idCardNo.length() - 7);
    }

    /**
     * 对大陆身份证号进行部分隐藏处理的简便方法，默认不做验证，由使用者自己保障。
     *
     * @param idCardNo 待部分隐藏处理的身份证号。
     * @return 如果hideFlag为true，返回符合《zhifubao会员信息展示规范》的身份证号部分展示字符串；否则返回原数据。
     */
    public static String idCardNoHide(final String idCardNo) {
        return idCardNoHide(idCardNo, false);
    }

    /**
     * 对银行卡号进行部分隐藏处理，只显示前6位和后4位，其他用*代替。<br/>
     * 如果doValidate为true且传入的数据不是合法的银行号，将按敏感信息缺省隐藏方式处理，显示前1/3和后1/3，其他用*代替。<br/>
     *
     * @param bankCardNo 待部分隐藏处理的银行卡号。
     * @param doValidate 是否做银行卡号合法性校验。警告：做校验会进行正则匹配，性能上比不做校验的方法略有损耗。
     * @return 如果hideFlag为true，返回符合《zhifubao会员信息展示规范》的银行卡号部分展示字符串；否则返回原数据。
     */
    public static String bankCardNoHide(final String bankCardNo, final boolean doValidate) {
        if (!needHide()) {
            return bankCardNo;
        }

        if (isBlank(bankCardNo)) {
            return bankCardNo;
        }

        if (doValidate) {
            if (!isBankCardNo(bankCardNo)) {
                // 不是银行卡号，按缺省的隐藏显示方法。
                return defaultHide(bankCardNo);
            }
        }
        return customizeHide(bankCardNo, 6, 4, bankCardNo.length() - 10);
    }

    /**
     * 对银行卡号进行部分隐藏处理的简便方法，默认不做验证，由使用者自己保障。
     *
     * @param bankCardNo 待部分隐藏处理的银行卡号。
     * @return 如果hideFlag为true，返回符合《zhifubao会员信息展示规范》的银行卡号部分展示字符串；否则返回原数据。
     */
    public static String bankCardNoHide(final String bankCardNo) {
        return bankCardNoHide(bankCardNo, false);
    }

    /**
     * 对电话号码（手机号或座机号码）进行部分隐藏处理，手机号只显示前3位和后4位，固定电话号码只显示区号和后4位，其他用*代替。<br/>
     * <strong> 不支持带分机的电话号码 ，并限于大陆地区手机号</strong> <br/>
     * 如果doValidate为true且传入的数据不是合法的电话号码，将按敏感信息缺省隐藏方式处理，显示前1/3和后1/3，其他用*代替。
     *
     * @param phoneOrTelNo 待隐藏处理的电话号码
     * @param doValidate   是否做电话号码合法性校验。警告：做校验会进行正则匹配，性能上比不做校验的方法略有损耗。
     * @return 如果hideFlag为true，返回《zhifubao会员信息展示规范》中推荐的电话号码部分展示字符串；否则返回原数据。
     */
    public static String phoneOrTelNoHide(final String phoneOrTelNo, final boolean doValidate) {
        if (!needHide()) {
            return phoneOrTelNo;
        }

        if (isBlank(phoneOrTelNo)) {
            return phoneOrTelNo;
        }
        String tmp = phoneOrTelNo.trim();
        if (doValidate) {
            if (!isPhoneOrTelNo(tmp)) {
                // 不是电话号码，按缺省的隐藏显示方法。
                return defaultHide(tmp);
            }
        }
        // phoneOrTelNo以1开头，认为是手机号，前3，否则前4
        int frontCharNum = (tmp.charAt(0) == '1') ? 3 : 4;
        // 是固定电话号码
        if (tmp.indexOf("-") > 0) {
            frontCharNum = tmp.indexOf("-") + 1;
        }
        return customizeHide(tmp, frontCharNum, 4, tmp.length() - 4 - frontCharNum);
    }

    /**
     * 对电话号码（手机号或座机号码）进行部分隐藏处理的简便方法，默认不做验证，由使用者自己保障。
     *
     * @param phoneOrTelNo 待隐藏处理的电话号码
     * @return 如果hideFlag为true，返回《zhifubao会员信息展示规范》中推荐的电话号码部分展示字符串；否则返回原数据。
     */
    public static String phoneOrTelNoHide(final String phoneOrTelNo) {
        return phoneOrTelNoHide(phoneOrTelNo, false);
    }

    /**
     * 手机号码通用隐藏规则（包括港澳台地区），隐藏中间四位 适用于网站以及客户端
     * <li>SensitiveUtils.cellphoneHide("13071835358") = 130****5358
     * <li>SensitiveUtils.cellphoneHide("3071835358") = 307****358
     * <li>SensitiveUtils.cellphoneHide("071835358") = 07****358
     * <li>SensitiveUtils.cellphoneHide("835358") = 8****8
     *
     * @param cellphone
     * @return 隐藏后的手机号码
     */
    public static String cellphoneHide(final String cellphone) {
        if (!needHide()) {
            return cellphone;
        }

        if (isBlank(cellphone)) {
            return cellphone;
        }
        String tmp = cellphone.trim();
        int notHideNum = tmp.length() - 4;
        return customizeHide(tmp, notHideNum / 2, notHideNum - notHideNum / 2, 4);
    }

    /**
     * 手机号码通用隐藏规则（包括港澳台地区）， 适用于短信。
     * SensitiveUtils.cellphoneHideSMS("13071835358") = 130*5358
     * SensitiveUtils.cellphoneHideSMS("3071835358") = 307*358
     * SensitiveUtils.cellphoneHideSMS("071835358") = 71*358
     * SensitiveUtils.cellphoneHideSMS("835358") = 8*8
     *
     * @param cellphone
     * @return 隐藏后的手机号码
     */
    public static String cellphoneHideSMS(final String cellphone) {
        if (!needHide()) {
            return cellphone;
        }

        if (isBlank(cellphone)) {
            return cellphone;
        }
        String tmp = cellphone.trim();
        int notHideNum = tmp.length() - 4;
        return customizeHide(tmp, notHideNum / 2, notHideNum - notHideNum / 2, 1);

    }

    /**
     * 对Email进行部分隐藏处理，只显示用户名的前3位+***+@域名。如用户名不足3位，将显示用户名全部+***+@域名。</br>
     * 如果doValidate为true且传入的数据不是email（不含‘@’）,将按敏感信息缺省隐藏方式处理，显示前1/3和后1/3。
     *
     * @param email      待处理的Email
     * @param doValidate 是否做Email合法性校验。警告：做校验会进行正则匹配，性能上比不做校验的方法略有损耗。
     * @return 如果hideFlag为true，返回《zhifubao会员信息展示规范》中推荐的Email部分展示字符串；否则返回原数据。
     */
    public static String emailHide(final String email, final boolean doValidate) {
        if (!needHide()) {
            return email;
        }

        if (isBlank(email)) {
            return email;
        }

        if (doValidate) {
            if (!isEmail(email)) {
                // 不是email账号，按缺省的隐藏显示方法。
                return defaultHide(email);
            }
        }
        String tmp = email.trim();
        int atPos = tmp.indexOf('@');
        int frontNum = atPos < 3 ? atPos : 3;
        return customizeHide(tmp, frontNum, tmp.length() - atPos, 3);
    }

    /**
     * 对Email进行部分隐藏处理的简便方法，默认不做验证，由使用者自己保障。
     *
     * @param email 待处理的Email
     * @return 如果hideFlag为true，返回《zhifubao会员信息展示规范》中推荐的Email部分展示字符串；否则返回原数据。
     */
    public static String emailHide(final String email) {
        return emailHide(email, false);
    }

    /**
     * 对短信中的Email进行部分隐藏处理，只显示用户名的前3位+*+@域名。如用户名不足3位，将显示用户名全部+*+@域名。</br>
     * 如果doValidate为true且传入的数据不是email（不含‘@’）,将按敏感信息缺省隐藏方式处理，显示前1/3和后1/3。
     *
     * @param email      待处理的Email
     * @param doValidate 是否做Email合法性校验。警告：做校验会进行正则匹配，性能上比不做校验的方法略有损耗。
     * @return 如果hideFlag为true，返回《zhifubao会员信息展示规范》中推荐的Email部分展示字符串；否则返回原数据。
     */
    public static String emailHideSMS(final String email, final boolean doValidate) {
        if (!needHide()) {
            return email;
        }

        if (isBlank(email)) {
            return email;
        }

        if (doValidate) {
            if (!isEmail(email)) {
                // 不是email账号，按缺省的隐藏显示方法。
                return defaultHide(email);
            }
        }
        StringBuilder result = new StringBuilder();
        String tmp = email.trim();
        int atPos = tmp.indexOf('@');
        int frontNum = atPos < 3 ? atPos : 3;
        result.append(tmp.substring(0, frontNum));
        result.append("*@");
        String backStr = tmp.substring(atPos + 1);
        int dotIndex = backStr.indexOf('.');
        if (dotIndex <= 7) {
            result.append(backStr.substring(0, dotIndex)).append(".*");
        } else {
            result.append(backStr.substring(0, 7)).append("*");
        }
        return result.toString();

    }

    /**
     * 对短信中的Email进行部分隐藏处理的简便方法，默认不做验证，由使用者自己保障。 <li>
     * SensitiveUtils.emailHideSMS("ddddddd@hide.com") = ddd*@hide.* <li>
     * SensitiveUtils.emailHideSMS("ddddddd@netvigator.com") = ddd*@netviga*
     * <li>SensitiveUtils.emailHideSMS("d@netvigator.com") = d*@netviga* <li>
     * SensitiveUtils.emailHideSMS("ddddddd@outlook.com") = ddd*@outlook.*
     *
     * @param email 待处理的Email
     * @return 如果hideFlag为true，返回《zhifubao会员信息展示规范》中推荐的Email部分展示字符串；否则返回原数据。
     */
    public static String emailHideSMS(final String email) {
        return emailHideSMS(email, false);
    }

    /**
     * <p>
     * 过滤字符串中的敏感信息：对起始标签<code>tagBegin</code>和结尾标签<code>tagEnd</code>
     * 中间的内容按指定的敏感数据类型<code>sensitiveInfoType</code>进行部分隐藏。
     * </p>
     * <p>
     * 示例: 过滤证件号<br/>
     * <br/>
     * sourceStr为：
     *
     * <pre>
     *  &lt;Party id=&quot;part_2&quot;&gt;
     *   &lt;FullName&gt;yimin.jiang&lt;/FullName&gt;
     *   &lt;GovtIDTC tc=&quot;802&quot;&gt;&lt;/GovtIDTC&gt;
     *   &lt;GovtID&gt;432926201110191188&lt;/GovtID&gt;
     *   &lt;GovtTermDate&gt;2011-06-07&lt;/GovtTermDate&gt;
     *  &lt;/Party&gt;
     * </pre>
     *
     * <pre>
     * <code>tagBegin:&lt;GovtID&gt;</code>
     * <code>tagEnd: &lt;/GovtID&gt;</code>
     * <code>sensitiveInfoType: SensitiveUtils.IDCARDNO_DATA</code>
     * </pre>
     * <p>
     * 方法返回值:
     *
     * <pre>
     *  &lt;Party id=&quot;part_2&quot;&gt;
     *   &lt;FullName&gt;yimin.jiang&lt;/FullName&gt;
     *   &lt;GovtIDTC tc=&quot;802&quot;&gt;&lt;/GovtIDTC&gt;
     *   &lt;GovtID&gt;432926********1188&lt;/GovtID&gt;
     *   &lt;GovtTermDate&gt;2011-06-07&lt;/GovtTermDate&gt;
     *  &lt;/Party&gt;
     * </pre>
     *
     * @param sourceStr         源字符串
     * @param tagBegin          起始标签
     * @param tagEnd            结尾标签
     * @param sensitiveDataType 敏感数据类型，值可为：
     *                          <code>SensitiveUtils.BANKCARDNO_DATA<code>、<code>SensitiveUtils.IDCARDNO_DATA<code>、<code>SensitiveUtils.PHONENO_DATA<code>、<code>SensitiveUtils.EMAIL_DATA<code>或<code>SensitiveUtils.UNKNOWN_DATA<code>。
     * @return 如果hideFlag为true，返回过滤掉敏感数据后的字符串；否则返回原数据。
     */
    public static String filterHide(final String sourceStr, final String tagBegin,
                                    final String tagEnd, final int sensitiveDataType) {
        if (!needHide()) {
            return sourceStr;
        }

        if (isBlank(sourceStr)) {
            return sourceStr;
        }

        StringBuilder tmp = new StringBuilder(sourceStr);
        StringBuilder target = new StringBuilder();
        int begin = tmp.indexOf(tagBegin);
        int end = tmp.indexOf(tagEnd);
        // 逐段找到每一个匹配的节点，然后将节点内容替换
        while (begin != -1 && end != -1) {
            // 说明存在要求的节点
            target = target.append(tmp.toString().toCharArray(), 0, begin + tagBegin.length());
            // 加入过滤字符
            String coverReplace = "";
            switch (sensitiveDataType) {
                case BANKCARDNO_DATA:
                    coverReplace = bankCardNoHide(tmp.substring(begin + tagBegin.length(), end));
                    break;
                case IDCARDNO_DATA:
                    coverReplace = idCardNoHide(tmp.substring(begin + tagBegin.length(), end));
                    break;
                case PHONENO_DATA:
                    coverReplace = phoneOrTelNoHide(tmp.substring(begin + tagBegin.length(), end));
                    break;
                case EMAIL_DATA:
                    coverReplace = emailHide(tmp.substring(begin + tagBegin.length(), end));
                    break;
                default:
                    coverReplace = defaultHide(tmp.substring(begin + tagBegin.length(), end));
                    break;
            }
            target.append(coverReplace);
            target.append(tagEnd);
            // 用还未查找的剩余部分做新的目标串继续找
            tmp = new StringBuilder(tmp.substring(end + tagEnd.length()));
            begin = tmp.indexOf(tagBegin);
            end = tmp.indexOf(tagEnd);
        }
        // 加上最后一节
        target.append(tmp);
        return target.toString();
    }

    /**
     * 显示首/尾各1位，中间加**
     *
     * <li>SensitiveUtils.taobaoNickHide("中华") = 中**华 <li>
     * SensitiveUtils.taobaoNickHide("中华人") = 中**人 <li>
     * SensitiveUtils.taobaoNickHide("中华人民") = 中**民 <li>
     * SensitiveUtils.taobaoNickHide("china") = c**a
     *
     * @param sensitiveData 淘宝昵称
     * @return 部分隐藏后的昵称
     */
    public static String taobaoNickHide(final String sensitiveData) {
        if (!needHide()) {
            return sensitiveData;
        }

        if (isBlank(sensitiveData)) {
            return sensitiveData;
        }
        String tmp = sensitiveData.trim();
        return customizeHide(tmp, 1, 1, 2);
    }

    /**
     * 敏感信息缺省隐藏方式处理：显示前1/3和后1/3，其他*号代替。内容长度不能被3整除时，显示前ceil[length/3.0]和后floor[
     * length/3.0]
     *
     * <pre>
     * SensitiveUtils.defaultHide("ttt") = "t*t"
     * SensitiveUtils.defaultHide("tttttttt") = "ttt***tt"
     * </pre>
     *
     * @param sensitiveData 待部分隐藏处理的敏感信息。
     * @return 屏蔽后的数据; 如果hideFlag为flase，返回原数据。
     */
    public static String defaultHide(final String sensitiveData) {
        if (!needHide()) {
            return sensitiveData;
        }

        if (isBlank(sensitiveData)) {
            return sensitiveData;
        }
        String tmp = sensitiveData.trim();
        int length = tmp.length();
        int headNum = (int) Math.ceil(length * 1 / 3.0);
        int tailNum = (int) Math.floor(length * 1 / 3.0);
        return customizeHide(tmp, headNum, tailNum, length - headNum - tailNum);
    }


    /*public static void main(String[] args) {
        System.out.println(defaultHide("hh"));
        System.out.println(defaultHide("13333333333"));
        System.out.println(defaultHide("杭州市蒋村街道如意苑14"));
        System.out.println(defaultHide("王好人"));
        System.out.println(defaultHide("里斯"));
        System.out.println(defaultHide(""));
    }*/

    /**
     * 自定义屏蔽位数和屏蔽位置
     *
     * <pre>
     * SensitiveUtils.customizeHide("13568794561",3,4,4) = "135****4561"
     * SensitiveUtils.customizeHide("13568794561",0,4,4) = "****4561"
     * SensitiveUtils.customizeHide("13568794561",3,0,4) = "135****"
     * SensitiveUtils.customizeHide("13568794561",3,0,8) = "135********"
     * </pre>
     *
     * @param sensitiveData 原数据
     * @param frontCharNum  展示前几位
     * @param tailCharNum   展示后几位
     * @param hiddenCharNum 展示星号*的个数
     * @return 部分隐藏的敏感数据字符串
     */
    public static String customizeHide(final String sensitiveData, final int frontCharNum,
                                       final int tailCharNum, final int hiddenCharNum) {
        if (isBlank(sensitiveData)) {
            return sensitiveData;
        }
        String tmp = sensitiveData.trim();
        int length = tmp.length();
        // 合法性检查，如果参数不合法，返回源数据内容
        if (frontCharNum < 0 || tailCharNum < 0 || hiddenCharNum < 0
                || frontCharNum + tailCharNum > length) {
            return tmp;
        }

        int beginIndex = frontCharNum - 1;
        int endIndex = length - tailCharNum;

        // 原数据前半部分
        StringBuilder result = new StringBuilder();
        if (beginIndex >= 0 && beginIndex < length) {
            result.append(tmp.substring(0, frontCharNum));
        }

        // 中间*
        for (int i = 0; i < hiddenCharNum; i++) {
            result.append('*');
        }

        // 原数据后半部分
        if (endIndex >= 0 && endIndex < length) {
            result.append(tmp.substring(endIndex));
        }

        return result.toString();
    }

    /**
     * 简单判断是否为空字符串
     *
     * @param str
     * @return
     */
    private static boolean isBlank(final String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 通过正则表达式"[0-9]{15}|[0-9]{18}|[0-9]{14}X|[0-9]{17}X"来判断是否是合法的大陆身份证号。
     *
     * @param idCardNo
     * @return
     */
    private static boolean isIdCardNo(final String idCardNo) {
        if (isBlank(idCardNo)) {
            return false;
        } else {
            Matcher matcher = ID_CARD_PATTERN.matcher(idCardNo.trim());
            return matcher.matches();
        }
    }

    /**
     * 通过正则表达式"[0-9]{13,19}"来判断是否是合法的银行卡号。
     *
     * @param bankCardNo
     * @return 指示入参是否银行卡号的布尔值。
     */
    private static boolean isBankCardNo(final String bankCardNo) {
        if (isBlank(bankCardNo)) {
            return false;
        } else {
            Matcher matcher = BANK_CARD_PATTERN.matcher(bankCardNo.trim());
            return matcher.matches();
        }
    }

    /**
     * 通过正则表达式"[0-9]{3,4}[-]?[0-9]{7,8}"来判断是否是合法的电话号码。
     *
     * @param phoneOrTelNo
     * @return 指示入参是否电话号码的布尔值。
     */
    private static boolean isPhoneOrTelNo(final String phoneOrTelNo) {
        if (isBlank(phoneOrTelNo)) {
            return false;
        } else {
            Matcher matcher = PHONE_TEL_PATTERN.matcher(phoneOrTelNo.trim());
            return matcher.matches();
        }
    }

    /**
     * hideFlag setter
     *
     * @param hideFlag
     */
    public static void setHideFlag(final boolean hideFlag) {
        SensitiveUtils.hideFlag = hideFlag;
    }

    /**
     * hideFlag getter
     *
     * @return 是否需要进行隐藏屏蔽
     */
    public static boolean needHide() {
        return hideFlag;
    }

}
