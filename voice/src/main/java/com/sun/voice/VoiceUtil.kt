package com.sun.voice

import android.content.Context
import android.media.MediaPlayer
import android.media.PlaybackParams

object VoiceUtil {

    var mediaPlayer: MediaPlayer? = null;
    var isPlaying = false

    fun playVoiceString(context: Context, index: Int, voiceString: String) {
        var createState = false;
        if (mediaPlayer == null) mediaPlayer = null
        VoiceLog.log("加载音频[$index]")
        mediaPlayer = createVoice(context, index, voiceString);
        createState = true;
        if (createState)
            VoiceLog.log("加载音频[$index]成功")
        else
            VoiceLog.log("加载音频[$index]失败")

        mediaPlayer!!.setOnCompletionListener {
            it.release()
            var newVoiceIndex = index;
            VoiceLog.log("释放资源[$index]")
            if (index < voiceString.length) {
                newVoiceIndex++
                playVoiceString(context, newVoiceIndex, voiceString)
            } else {
                isPlaying = false
            }
        }

        mediaPlayer!!.prepare()
        mediaPlayer!!.start()
        VoiceLog.log("播放音频[$index]")
    }

    /**
     * 常用汉字
     * 一 乙
     * 二 十 丁 厂 七 卜 人 入 八 九 几 儿 了 力 乃 刀 又
     * 三 于 干 亏 士 工 土 才 寸 下 大 丈 与 万 上 小 口 巾 山 千
     * 乞 川 亿 个 勺 久 凡 及 夕 丸 么 广 亡 门 义 之 尸 弓 己 已
     * 子 卫 也 女 飞 刃 习 叉 马 乡
     * 丰 王 井 开 夫 天 无 元 专 云 扎 艺 木 五 支 厅 不 太 犬 区
     * 历 尤 友 匹 车 巨 牙 屯 比 互 切 瓦 止 少 日 中 冈 贝 内 水
     * 见 午 牛 手 毛 气 升 长 仁 什 片 仆 化 仇 币 仍 仅 斤 爪 反
     * 介 父 从 今 凶 分 乏 公 仓 月 氏 勿 欠 风 丹 匀 乌 凤 勾 文
     * 六 方 火 为 斗 忆 订 计 户 认 心 尺 引 丑 巴 孔 队 办 以 允
     * 予 劝 双 书 幻
     * 玉 刊 示 末 未 击 打 巧 正 扑 扒 功 扔 去 甘 世 古 节 本 术
     * 汇 头 汉 宁 穴 它 讨 写 让 礼 训 必 议 讯 记 永 司 尼 民 出
     * 汁 丙 左 厉 右 石 布 龙 平 灭 轧 东 卡 北 占 业 旧 帅 归 且
     * 旦 目 叶 甲 申 叮 电 号 田 由 史 只 央 兄 叼 叫 另 叨 叹 四
     * 生 失 禾 丘 付 仗 代 仙 们 仪 白 仔 他 斥 瓜 乎 丛 令 用 甩
     * 印 乐 句 匆 册 犯 外 处 冬 鸟 务 包 饥 主 市 立 闪 兰 半 辽
     * 奶 奴 加 召 皮 边 发 孕 圣 对 台 矛 纠 母 幼 丝 可
     * 式 刑 动 扛 寺 吉 扣 考 托 老 执 巩 圾 扩 扫 地 扬 场 耳 共
     * 芒 亚 芝 朽 朴 机 权 过 臣 再 协 西 压 厌 在 有 百 存 而 页
     * 匠 夸 夺 灰 达 列 死 成 夹 轨 邪 划 迈 毕 至 此 贞 师 尘 尖
     * 劣 光 当 早 吐 吓 虫 曲 团 同 吊 吃 因 吸 吗 屿 帆 岁 回 岂
     * 刚 则 肉 网 年 朱 先 丢 舌 竹 迁 乔 伟 传 乒 乓 休 伍 伏 优
     * 伐 延 件 任 伤 价 份 华 仰 仿 伙 伪 自 血 向 似 后 行 舟 全
     * 会 杀 合 兆 企 众 爷 伞 创 肌 朵 杂 危 旬 旨 负 各 名 多 争
     * 色 壮 冲 冰 庄 庆 亦 刘 齐 交 次 衣 产 决 充 妄 闭 问 闯 羊
     * 并 关 米 灯 州 汗 污 江 池 汤 忙 兴 宇 守 宅 字 安 讲 军 许
     * 论 农 讽 设 访 寻 那 迅 尽 导 异 孙 阵 阳 收 阶 阴 防 奸 如
     * 妇 好 她 妈 戏 羽 观 欢 买 红 纤 级 约 纪 驰 巡
     * 寿 弄 麦 形 进 戒 吞 远 违 运 扶 抚 坛 技 坏 扰 拒 找 批 扯
     * 址 走 抄 坝 贡 攻 赤 折 抓 扮 抢 孝 均 抛 投 坟 抗 坑 坊 抖
     * 护 壳 志 扭 块 声 把 报 却 劫 芽 花 芹 芬 苍 芳 严 芦 劳 克
     * 苏 杆 杠 杜 材 村 杏 极 李 杨 求 更 束 豆 两 丽 医 辰 励 否
     * 还 歼 来 连 步 坚 旱 盯 呈 时 吴 助 县 里 呆 园 旷 围 呀 吨
     * 足 邮 男 困 吵 串 员 听 吩 吹 呜 吧 吼 别 岗 帐 财 针 钉 告
     * 我 乱 利 秃 秀 私 每 兵 估 体 何 但 伸 作 伯 伶 佣 低 你 住
     * 位 伴 身 皂 佛 近 彻 役 返 余 希 坐 谷 妥 含 邻 岔 肝 肚 肠
     * 龟 免 狂 犹 角 删 条 卵 岛 迎 饭 饮 系 言 冻 状 亩 况 床 库
     * 疗 应 冷 这 序 辛 弃 冶 忘 闲 间 闷 判 灶 灿 弟 汪 沙 汽 沃
     * 泛 沟 没 沈 沉 怀 忧 快 完 宋 宏 牢 究 穷 灾 良 证 启 评 补
     * 初 社 识 诉 诊 词 译 君 灵 即 层 尿 尾 迟 局 改 张 忌 际 陆
     * 阿 陈 阻 附 妙 妖 妨 努 忍 劲 鸡 驱 纯 纱 纳 纲 驳 纵 纷 纸
     * 纹 纺 驴 纽
     * 奉 玩 环 武 青 责 现 表 规 抹 拢 拔 拣 担 坦 押 抽 拐 拖 拍
     * 者 顶 拆 拥 抵 拘 势 抱 垃 拉 拦 拌 幸 招 坡 披 拨 择 抬 其
     * 取 苦 若 茂 苹 苗 英 范 直 茄 茎 茅 林 枝 杯 柜 析 板 松 枪
     * 构 杰 述 枕 丧 或 画 卧 事 刺 枣 雨 卖 矿 码 厕 奔 奇 奋 态
     * 欧 垄 妻 轰 顷 转 斩 轮 软 到 非 叔 肯 齿 些 虎 虏 肾 贤 尚
     * 旺 具 果 味 昆 国 昌 畅 明 易 昂 典 固 忠 咐 呼 鸣 咏 呢 岸
     * 岩 帖 罗 帜 岭 凯 败 贩 购 图 钓 制 知 垂 牧 物 乖 刮 秆 和
     * 季 委 佳 侍 供 使 例 版 侄 侦 侧 凭 侨 佩 货 依 的 迫 质 欣
     * 征 往 爬 彼 径 所 舍 金 命 斧 爸 采 受 乳 贪 念 贫 肤 肺 肢
     * 肿 胀 朋 股 肥 服 胁 周 昏 鱼 兔 狐 忽 狗 备 饰 饱 饲 变 京
     * 享 店 夜 庙 府 底 剂 郊 废 净 盲 放 刻 育 闸 闹 郑 券 卷 单
     * 炒 炊 炕 炎 炉 沫 浅 法 泄 河 沾 泪 油 泊 沿 泡 注 泻 泳 泥
     * 沸 波 泼 泽 治 怖 性 怕 怜 怪 学 宝 宗 定 宜 审 宙 官 空 帘
     * 实 试 郎 诗 肩 房 诚 衬 衫 视 话 诞 询 该 详 建 肃 录 隶 居
     * 届 刷 屈 弦 承 孟 孤 陕 降 限 妹 姑 姐 姓 始 驾 参 艰 线 练
     * 组 细 驶 织 终 驻 驼 绍 经 贯
     * 奏 春 帮 珍 玻 毒 型 挂 封 持 项 垮 挎 城 挠 政 赴 赵 挡 挺
     * 括 拴 拾 挑 指 垫 挣 挤 拼 挖 按 挥 挪 某 甚 革 荐 巷 带 草
     * 茧 茶 荒 茫 荡 荣 故 胡 南 药 标 枯 柄 栋 相 查 柏 柳 柱 柿
     * 栏 树 要 咸 威 歪 研 砖 厘 厚 砌 砍 面 耐 耍 牵 残 殃 轻 鸦
     * 皆 背 战 点 临 览 竖 省 削 尝 是 盼 眨 哄 显 哑 冒 映 星 昨
     * 畏 趴 胃 贵 界 虹 虾 蚁 思 蚂 虽 品 咽 骂 哗 咱 响 哈 咬 咳
     * 哪 炭 峡 罚 贱 贴 骨 钞 钟 钢 钥 钩 卸 缸 拜 看 矩 怎 牲 选
     * 适 秒 香 种 秋 科 重 复 竿 段 便 俩 贷 顺 修 保 促 侮 俭 俗
     * 俘 信 皇 泉 鬼 侵 追 俊 盾 待 律 很 须 叙 剑 逃 食 盆 胆 胜
     * 胞 胖 脉 勉 狭 狮 独 狡 狱 狠 贸 怨 急 饶 蚀 饺 饼 弯 将 奖
     * 哀 亭 亮 度 迹 庭 疮 疯 疫 疤 姿 亲 音 帝 施 闻 阀 阁 差 养
     * 美 姜 叛 送 类 迷 前 首 逆 总 炼 炸 炮 烂 剃 洁 洪 洒 浇 浊
     * 洞 测 洗 活 派 洽 染 济 洋 洲 浑 浓 津 恒 恢 恰 恼 恨 举 觉
     * 宣 室 宫 宪 突 穿 窃 客 冠 语 扁 袄 祖 神 祝 误 诱 说 诵 垦
     * 退 既 屋 昼 费 陡 眉 孩 除 险 院 娃 姥 姨 姻 娇 怒 架 贺 盈
     * 勇 怠 柔 垒 绑 绒 结 绕 骄 绘 给 络 骆 绝 绞 统
     * 耕 耗 艳 泰 珠 班 素 蚕 顽 盏 匪 捞 栽 捕 振 载 赶 起 盐 捎
     * 捏 埋 捉 捆 捐 损 都 哲 逝 捡 换 挽 热 恐 壶 挨 耻 耽 恭 莲
     * 莫 荷 获 晋 恶 真 框 桂 档 桐 株 桥 桃 格 校 核 样 根 索 哥
     * 速 逗 栗 配 翅 辱 唇 夏 础 破 原 套 逐 烈 殊 顾 轿 较 顿 毙
     * 致 柴 桌 虑 监 紧 党 晒 眠 晓 鸭 晃 晌 晕 蚊 哨 哭 恩 唤 啊
     * 唉 罢 峰 圆 贼 贿 钱 钳 钻 铁 铃 铅 缺 氧 特 牺 造 乘 敌 秤
     * 租 积 秧 秩 称 秘 透 笔 笑 笋 债 借 值 倚 倾 倒 倘 俱 倡 候
     * 俯 倍 倦 健 臭 射 躬 息 徒 徐 舰 舱 般 航 途 拿 爹 爱 颂 翁
     * 脆 脂 胸 胳 脏 胶 脑 狸 狼 逢 留 皱 饿 恋 桨 浆 衰 高 席 准
     * 座 脊 症 病 疾 疼 疲 效 离 唐 资 凉 站 剖 竞 部 旁 旅 畜 阅
     * 羞 瓶 拳 粉 料 益 兼 烤 烘 烦 烧 烛 烟 递 涛 浙 涝 酒 涉 消
     * 浩 海 涂 浴 浮 流 润 浪 浸 涨 烫 涌 悟 悄 悔 悦 害 宽 家 宵
     * 宴 宾 窄 容 宰 案 请 朗 诸 读 扇 袜 袖 袍 被 祥 课 谁 调 冤
     * 谅 谈 谊 剥 恳 展 剧 屑 弱 陵 陶 陷 陪 娱 娘 通 能 难 预 桑
     * 绢 绣 验 继
     * 球 理 捧 堵 描 域 掩 捷 排 掉 堆 推 掀 授 教 掏 掠 培 接 控
     * 探 据 掘 职 基 著 勒 黄 萌 萝 菌 菜 萄 菊 萍 菠 营 械 梦 梢
     * 梅 检 梳 梯 桶 救 副 票 戚 爽 聋 袭 盛 雪 辅 辆 虚 雀 堂 常
     * 匙 晨 睁 眯 眼 悬 野 啦 晚 啄 距 跃 略 蛇 累 唱 患 唯 崖 崭
     * 崇 圈 铜 铲 银 甜 梨 犁 移 笨 笼 笛 符 第 敏 做 袋 悠 偿 偶
     * 偷 您 售 停 偏 假 得 衔 盘 船 斜 盒 鸽 悉 欲 彩 领 脚 脖 脸
     * 脱 象 够 猜 猪 猎 猫 猛 馅 馆 凑 减 毫 麻 痒 痕 廊 康 庸 鹿
     * 盗 章 竟 商 族 旋 望 率 着 盖 粘 粗 粒 断 剪 兽 清 添 淋 淹
     * 渠 渐 混 渔 淘 液 淡 深 婆 梁 渗 情 惜 惭 悼 惧 惕 惊 惨 惯
     * 寇 寄 宿 窑 密 谋 谎 祸 谜 逮 敢 屠 弹 随 蛋 隆 隐 婚 婶 颈
     * 绩 绪 续 骑 绳 维 绵 绸 绿
     * 琴 斑 替 款 堪 搭 塔 越 趁 趋 超 提 堤 博 揭 喜 插 揪 搜 煮
     * 援 裁 搁 搂 搅 握 揉 斯 期 欺 联 散 惹 葬 葛 董 葡 敬 葱 落
     * 朝 辜 葵 棒 棋 植 森 椅 椒 棵 棍 棉 棚 棕 惠 惑 逼 厨 厦 硬
     * 确 雁 殖 裂 雄 暂 雅 辈 悲 紫 辉 敞 赏 掌 晴 暑 最 量 喷 晶
     * 喇 遇 喊 景 践 跌 跑 遗 蛙 蛛 蜓 喝 喂 喘 喉 幅 帽 赌 赔 黑
     * 铸 铺 链 销 锁 锄 锅 锈 锋 锐 短 智 毯 鹅 剩 稍 程 稀 税 筐
     * 等 筑 策 筛 筒 答 筋 筝 傲 傅 牌 堡 集 焦 傍 储 奥 街 惩 御
     * 循 艇 舒 番 释 禽 腊 脾 腔 鲁 猾 猴 然 馋 装 蛮 就 痛 童 阔
     * 善 羡 普 粪 尊 道 曾 焰 港 湖 渣 湿 温 渴 滑 湾 渡 游 滋 溉
     * 愤 慌 惰 愧 愉 慨 割 寒 富 窜 窝 窗 遍 裕 裤 裙 谢 谣 谦 属
     * 屡 强 粥 疏 隔 隙 絮 嫂 登 缎 缓 编 骗 缘
     * 瑞 魂 肆 摄 摸 填 搏 塌 鼓 摆 携 搬 摇 搞 塘 摊 蒜 勤 鹊 蓝
     * 墓 幕 蓬 蓄 蒙 蒸 献 禁 楚 想 槐 榆 楼 概 赖 酬 感 碍 碑 碎
     * 碰 碗 碌 雷 零 雾 雹 输 督 龄 鉴 睛 睡 睬 鄙 愚 暖 盟 歇 暗
     * 照 跨 跳 跪 路 跟 遣 蛾 蜂 嗓 置 罪 罩 错 锡 锣 锤 锦 键 锯
     * 矮 辞 稠 愁 筹 签 简 毁 舅 鼠 催 傻 像 躲 微 愈 遥 腰 腥 腹
     * 腾 腿 触 解 酱 痰 廉 新 韵 意 粮 数 煎 塑 慈 煤 煌 满 漠 源
     * 滤 滥 滔 溪 溜 滚 滨 粱 滩 慎 誉 塞 谨 福 群 殿 辟 障 嫌 嫁
     * 叠 缝 缠
     * 静 碧 璃 墙 撇 嘉 摧 截 誓 境 摘 摔 聚 蔽 慕 暮 蔑 模 榴 榜
     * 榨 歌 遭 酷 酿 酸 磁 愿 需 弊 裳 颗 嗽 蜻 蜡 蝇 蜘 赚 锹 锻
     * 舞 稳 算 箩 管 僚 鼻 魄 貌 膜 膊 膀 鲜 疑 馒 裹 敲 豪 膏 遮
     * 腐 瘦 辣 竭 端 旗 精 歉 熄 熔 漆 漂 漫 滴 演 漏 慢 寨 赛 察
     * 蜜 谱 嫩 翠 熊 凳 骡 缩
     * 慧 撕 撒 趣 趟 撑 播 撞 撤 增 聪 鞋 蕉 蔬 横 槽 樱 橡 飘 醋
     * 醉 震 霉 瞒 题 暴 瞎 影 踢 踏 踩 踪 蝶 蝴 嘱 墨 镇 靠 稻 黎
     * 稿 稼 箱 箭 篇 僵 躺 僻 德 艘 膝 膛 熟 摩 颜 毅 糊 遵 潜 潮
     * 懂 额 慰 劈
     * 操 燕 薯 薪 薄 颠 橘 整 融 醒 餐 嘴 蹄 器 赠 默 镜 赞 篮 邀
     * 衡 膨 雕 磨 凝 辨 辩 糖 糕 燃 澡 激 懒 壁 避 缴
     * 戴 擦 鞠 藏 霜 霞 瞧 蹈 螺 穗 繁 辫 赢 糟 糠 燥 臂 翼 骤
     * 鞭 覆 蹦 镰 翻 鹰
     * 警 攀 蹲 颤 瓣 爆 疆
     * 壤 耀 躁 嚼 嚷 籍 魔 灌
     * 蠢 霸 露
     * 囊
     * 罐
     *
     * 次常用字
     * 匕 刁
     * 丐 歹 戈 夭 仑 讥 冗 邓
     * 艾 夯 凸 卢 叭 叽 皿 凹 囚 矢 乍 尔 冯 玄
     * 邦 迂 邢 芋 芍 吏 夷 吁 吕 吆 屹 廷 迄 臼 仲 伦 伊 肋 旭 匈
     * 凫 妆 亥 汛 讳 讶 讹 讼 诀 弛 阱 驮 驯 纫
     * 玖 玛 韧 抠 扼 汞 扳 抡 坎 坞 抑 拟 抒 芙 芜 苇 芥 芯 芭 杖
     * 杉 巫 杈 甫 匣 轩 卤 肖 吱 吠 呕 呐 吟 呛 吻 吭 邑 囤 吮 岖
     * 牡 佑 佃 伺 囱 肛 肘 甸 狈 鸠 彤 灸 刨 庇 吝 庐 闰 兑 灼 沐
     * 沛 汰 沥 沦 汹 沧 沪 忱 诅 诈 罕 屁 坠 妓 姊 妒 纬
     * 玫 卦 坷 坯 拓 坪 坤 拄 拧 拂 拙 拇 拗 茉 昔 苛 苫 苟 苞 茁
     * 苔 枉 枢 枚 枫 杭 郁 矾 奈 奄 殴 歧 卓 昙 哎 咕 呵 咙 呻 咒
     * 咆 咖 帕 账 贬 贮 氛 秉 岳 侠 侥 侣 侈 卑 刽 刹 肴 觅 忿 瓮
     * 肮 肪 狞 庞 疟 疙 疚 卒 氓 炬 沽 沮 泣 泞 泌 沼 怔 怯 宠 宛
     * 衩 祈 诡 帚 屉 弧 弥 陋 陌 函 姆 虱 叁 绅 驹 绊 绎
     * 契 贰 玷 玲 珊 拭 拷 拱 挟 垢 垛 拯 荆 茸 茬 荚 茵 茴 荞 荠
     * 荤 荧 荔 栈 柑 栅 柠 枷 勃 柬 砂 泵 砚 鸥 轴 韭 虐 昧 盹 咧
     * 昵 昭 盅 勋 哆 咪 哟 幽 钙 钝 钠 钦 钧 钮 毡 氢 秕 俏 俄 俐
     * 侯 徊 衍 胚 胧 胎 狰 饵 峦 奕 咨 飒 闺 闽 籽 娄 烁 炫 洼 柒
     * 涎 洛 恃 恍 恬 恤 宦 诫 诬 祠 诲 屏 屎 逊 陨 姚 娜 蚤 骇
     * 耘 耙 秦 匿 埂 捂 捍 袁 捌 挫 挚 捣 捅 埃 耿 聂 荸 莽 莱 莉
     * 莹 莺 梆 栖 桦 栓 桅 桩 贾 酌 砸 砰 砾 殉 逞 哮 唠 哺 剔 蚌
     * 蚜 畔 蚣 蚪 蚓 哩 圃 鸯 唁 哼 唆 峭 唧 峻 赂 赃 钾 铆 氨 秫
     * 笆 俺 赁 倔 殷 耸 舀 豺 豹 颁 胯 胰 脐 脓 逛 卿 鸵 鸳 馁 凌
     * 凄 衷 郭 斋 疹 紊 瓷 羔 烙 浦 涡 涣 涤 涧 涕 涩 悍 悯 窍 诺
     * 诽 袒 谆 祟 恕 娩 骏
     * 琐 麸 琉 琅 措 捺 捶 赦 埠 捻 掐 掂 掖 掷 掸 掺 勘 聊 娶 菱
     * 菲 萎 菩 萤 乾 萧 萨 菇 彬 梗 梧 梭 曹 酝 酗 厢 硅 硕 奢 盔
     * 匾 颅 彪 眶 晤 曼 晦 冕 啡 畦 趾 啃 蛆 蚯 蛉 蛀 唬 啰 唾 啤
     * 啥 啸 崎 逻 崔 崩 婴 赊 铐 铛 铝 铡 铣 铭 矫 秸 秽 笙 笤 偎
     * 傀 躯 兜 衅 徘 徙 舶 舷 舵 敛 翎 脯 逸 凰 猖 祭 烹 庶 庵 痊
     * 阎 阐 眷 焊 焕 鸿 涯 淑 淌 淮 淆 渊 淫 淳 淤 淀 涮 涵 惦 悴
     * 惋 寂 窒 谍 谐 裆 袱 祷 谒 谓 谚 尉 堕 隅 婉 颇 绰 绷 综 绽
     * 缀 巢
     * 琳 琢 琼 揍 堰 揩 揽 揖 彭 揣 搀 搓 壹 搔 葫 募 蒋 蒂 韩 棱
     * 椰 焚 椎 棺 榔 椭 粟 棘 酣 酥 硝 硫 颊 雳 翘 凿 棠 晰 鼎 喳
     * 遏 晾 畴 跋 跛 蛔 蜒 蛤 鹃 喻 啼 喧 嵌 赋 赎 赐 锉 锌 甥 掰
     * 氮 氯 黍 筏 牍 粤 逾 腌 腋 腕 猩 猬 惫 敦 痘 痢 痪 竣 翔 奠
     * 遂 焙 滞 湘 渤 渺 溃 溅 湃 愕 惶 寓 窖 窘 雇 谤 犀 隘 媒 媚
     * 婿 缅 缆 缔 缕 骚
     * 瑟 鹉 瑰 搪 聘 斟 靴 靶 蓖 蒿 蒲 蓉 楔 椿 楷 榄 楞 楣 酪 碘
     * 硼 碉 辐 辑 频 睹 睦 瞄 嗜 嗦 暇 畸 跷 跺 蜈 蜗 蜕 蛹 嗅 嗡
     * 嗤 署 蜀 幌 锚 锥 锨 锭 锰 稚 颓 筷 魁 衙 腻 腮 腺 鹏 肄 猿
     * 颖 煞 雏 馍 馏 禀 痹 廓 痴 靖 誊 漓 溢 溯 溶 滓 溺 寞 窥 窟
     * 寝 褂 裸 谬 媳 嫉 缚 缤 剿
     * 赘 熬 赫 蔫 摹 蔓 蔗 蔼 熙 蔚 兢 榛 榕 酵 碟 碴 碱 碳 辕 辖
     * 雌 墅 嘁 踊 蝉 嘀 幔 镀 舔 熏 箍 箕 箫 舆 僧 孵 瘩 瘟 彰 粹
     * 漱 漩 漾 慷 寡 寥 谭 褐 褪 隧 嫡 缨
     * 撵 撩 撮 撬 擒 墩 撰 鞍 蕊 蕴 樊 樟 橄 敷 豌 醇 磕 磅 碾 憋
     * 嘶 嘲 嘹 蝠 蝎 蝌 蝗 蝙 嘿 幢 镊 镐 稽 篓 膘 鲤 鲫 褒 瘪 瘤
     * 瘫 凛 澎 潭 潦 澳 潘 澈 澜 澄 憔 懊 憎 翩 褥 谴 鹤 憨 履 嬉
     * 豫 缭
     * 撼 擂 擅 蕾 薛 薇 擎 翰 噩 橱 橙 瓢 磺 霍 霎 辙 冀 踱 蹂 蟆
     * 螃 螟 噪 鹦 黔 穆 篡 篷 篙 篱 儒 膳 鲸 瘾 瘸 糙 燎 濒 憾 懈
     * 窿 缰
     * 壕 藐 檬 檐 檩 檀 礁 磷 瞭 瞬 瞳 瞪 曙 蹋 蟋 蟀 嚎 赡 镣 魏
     * 簇 儡 徽 爵 朦 臊 鳄 糜 癌 懦 豁 臀
     * 藕 藤 瞻 嚣 鳍 癞 瀑 襟 璧 戳
     * 攒 孽 蘑 藻 鳖 蹭 蹬 簸 簿 蟹 靡 癣 羹
     * 鬓 攘 蠕 巍 鳞 糯 譬
     * 霹 躏 髓
     * 蘸 镶 瓤
     * 矗
     */
    private fun createVoice(context: Context, index: Int, voiceString: String): MediaPlayer {
        var mp: MediaPlayer?
        var voiceChar = voiceString.substring(index - 1, index)
        when (voiceChar) {
            //a
            //b
            "八","巴","扒" -> mp = MediaPlayer.create(context, R.raw.ba1)
            "办"->mp = MediaPlayer.create(context,R.raw.ban4)
            "贝" -> mp = MediaPlayer.create(context, R.raw.bei4)
            "本"-> mp = MediaPlayer.create(context,R.raw.ben3)
            "比" -> mp = MediaPlayer.create(context, R.raw.bi3)
            "币"-> mp = MediaPlayer.create(context,R.raw.bi4)
            "卜" -> mp = MediaPlayer.create(context, R.raw.bu3)
            "不" -> mp = MediaPlayer.create(context, R.raw.bu4)
            //c
            "才" -> mp = MediaPlayer.create(context, R.raw.cai2)
            "仓"->mp = MediaPlayer.create(context,R.raw.cang1)
            "叉" -> mp = MediaPlayer.create(context, R.raw.cha1)
            "长"->mp = MediaPlayer.create(context,R.raw.chang2)
            "厂" -> mp = MediaPlayer.create(context, R.raw.chang3)
            "车" -> mp = MediaPlayer.create(context, R.raw.che1)
            "尺"->mp = MediaPlayer.create(context,R.raw.chi3)
            "仇"->mp = MediaPlayer.create(context,R.raw.chou2)
            "丑"->mp = MediaPlayer.create(context,R.raw.chou3)
            "川" -> mp = MediaPlayer.create(context, R.raw.chuan1)
            "从"->mp = MediaPlayer.create(context,R.raw.cong2)
            "寸" -> mp = MediaPlayer.create(context, R.raw.cun4)
            //d
            "打"-> mp = MediaPlayer.create(context,R.raw.da3)
            "大" -> mp = MediaPlayer.create(context, R.raw.da4)
            "丹"->mp = MediaPlayer.create(context,R.raw.dan1)
            "刀" -> mp = MediaPlayer.create(context, R.raw.dao1)
            "的" -> mp = MediaPlayer.create(context, R.raw.de0)
            "丁" -> mp = MediaPlayer.create(context, R.raw.ding1)
            "订"->mp = MediaPlayer.create(context,R.raw.ding4)
            "斗"-> mp = MediaPlayer.create(context,R.raw.dou4)
            "队"->mp = MediaPlayer.create(context,R.raw.dui4)
            //e
            "儿" -> mp = MediaPlayer.create(context, R.raw.er2)
            "二" -> mp = MediaPlayer.create(context, R.raw.er4)
            //f
            "乏"->mp = MediaPlayer.create(context,R.raw.fa2)
            "凡" -> mp = MediaPlayer.create(context, R.raw.fan2)
            "反"->mp = MediaPlayer.create(context,R.raw.fan3)
            "方"->mp = MediaPlayer.create(context,R.raw.fang1)
            "飞" -> mp = MediaPlayer.create(context, R.raw.fei1)
            "分"->mp = MediaPlayer.create(context,R.raw.fen1)
            "丰","风" -> mp = MediaPlayer.create(context, R.raw.feng1)
            "凤"->mp = MediaPlayer.create(context,R.raw.feng4)
            "夫" -> mp = MediaPlayer.create(context, R.raw.fu1)
            "父"-> mp = MediaPlayer.create(context,R.raw.fu4)
            //g
            "甘"-> mp = MediaPlayer.create(context,R.raw.gan1)
            "干" -> mp = MediaPlayer.create(context, R.raw.gan4)
            "个" -> mp = MediaPlayer.create(context, R.raw.ge4)
            "冈" -> mp = MediaPlayer.create(context, R.raw.gang1)
            "工", "弓","公","功" -> mp = MediaPlayer.create(context, R.raw.gong1)
            "勾"->mp = MediaPlayer.create(context,R.raw.gou1)
            "古"->mp = MediaPlayer.create(context,R.raw.gu3)
            "广" -> mp = MediaPlayer.create(context, R.raw.guang3)
            "国" -> mp = MediaPlayer.create(context, R.raw.guo2)
            //h
            "互","户" -> mp = MediaPlayer.create(context, R.raw.hu4)
            "化"-> mp = MediaPlayer.create(context,R.raw.hua4)
            "幻"-> mp = MediaPlayer.create(context,R.raw.huan4)
            "火"->mp = MediaPlayer.create(context,R.raw.huo3)
            //i
            //j
            "击"-> mp = MediaPlayer.create(context,R.raw.ji1)
            "及" -> mp = MediaPlayer.create(context, R.raw.ji2)
            "几", "己" -> mp = MediaPlayer.create(context, R.raw.ji3)
            "计"->mp = MediaPlayer.create(context,R.raw.ji4)
            "见"->mp = MediaPlayer.create(context,R.raw.jian4)
            "节"->mp = MediaPlayer.create(context,R.raw.jie2)
            "介"->mp = MediaPlayer.create(context,R.raw.jie4)
            "巾","斤","今" -> mp = MediaPlayer.create(context, R.raw.jin1)
            "仅"->mp = MediaPlayer.create(context,R.raw.jin3)
            "井" -> mp = MediaPlayer.create(context, R.raw.jing3)
            "九", "久" -> mp = MediaPlayer.create(context, R.raw.jiu3)
            "巨" -> mp = MediaPlayer.create(context, R.raw.ju4)
            //k
            "开" -> mp = MediaPlayer.create(context, R.raw.kai1)
            "刊"->mp = MediaPlayer.create(context,R.raw.kan1)
            "孔"->mp = MediaPlayer.create(context,R.raw.kong3)
            "口" -> mp = MediaPlayer.create(context, R.raw.kou3)
            "亏" -> mp = MediaPlayer.create(context, R.raw.kui1)
            //l
            "了" -> mp = MediaPlayer.create(context, R.raw.le0)
            "力", "历" -> mp = MediaPlayer.create(context, R.raw.li4)
            "六"->mp = MediaPlayer.create(context,R.raw.liu4)
            //m
            "马" -> mp = MediaPlayer.create(context, R.raw.ma3)
            "毛"->mp = MediaPlayer.create(context,R.raw.mao2)
            "么" -> mp = MediaPlayer.create(context, R.raw.me0)
            "门" -> mp = MediaPlayer.create(context, R.raw.men2)
            "末"-> mp = MediaPlayer.create(context,R.raw.mo4)
            "木" -> mp = MediaPlayer.create(context, R.raw.mu4)
            //n
            "乃" -> mp = MediaPlayer.create(context, R.raw.nai3)
            "内" -> mp = MediaPlayer.create(context, R.raw.nei4)
            "你" -> mp = MediaPlayer.create(context, R.raw.ni3)
            "牛"->mp = MediaPlayer.create(context,R.raw.niu2)
            "女" -> mp = MediaPlayer.create(context, R.raw.nu3)
            //o
            //p
            "匹" -> mp = MediaPlayer.create(context, R.raw.pi3)
            "片"->mp = MediaPlayer.create(context,R.raw.pian4)
            "扑"->mp = MediaPlayer.create(context,R.raw.pu1)
            "仆"->mp = MediaPlayer.create(context,R.raw.pu2)
            //q
            "七" -> mp = MediaPlayer.create(context, R.raw.qi1)
            "乞" -> mp = MediaPlayer.create(context, R.raw.qi3)
            "气"-> mp = MediaPlayer.create(context,R.raw.qi4)
            "千" -> mp = MediaPlayer.create(context, R.raw.qian1)
            "欠"->mp = MediaPlayer.create(context,R.raw.qian4)
            "巧"->mp = MediaPlayer.create(context,R.raw.qiao3)
            "切" -> mp = MediaPlayer.create(context, R.raw.qie1)
            "区" -> mp = MediaPlayer.create(context, R.raw.qu1)
            "去"-> mp = MediaPlayer.create(context,R.raw.qu4)
            "犬" -> mp = MediaPlayer.create(context, R.raw.quan3)
            "劝"-> mp = MediaPlayer.create(context,R.raw.quan4)
            //r
            "人","仁" -> mp = MediaPlayer.create(context, R.raw.ren2)
            "刃","认" -> mp = MediaPlayer.create(context, R.raw.ren4)
            "扔"->mp = MediaPlayer.create(context,R.raw.reng1)
            "仍"-> mp = MediaPlayer.create(context,R.raw.reng2)
            "日" -> mp = MediaPlayer.create(context, R.raw.ri4)
            "入" -> mp = MediaPlayer.create(context, R.raw.ru4)
            //s
            "三" -> mp = MediaPlayer.create(context, R.raw.san1)
            "山" -> mp = MediaPlayer.create(context, R.raw.shan1)
            "上" -> mp = MediaPlayer.create(context, R.raw.shang4)
            "勺" -> mp = MediaPlayer.create(context, R.raw.shao2)
            "少" -> mp = MediaPlayer.create(context, R.raw.shao3)
            "什"->mp = MediaPlayer.create(context,R.raw.shen2)
            "升"->mp = MediaPlayer.create(context,R.raw.sheng1)
            "尸" -> mp = MediaPlayer.create(context, R.raw.shi1)
            "十" -> mp = MediaPlayer.create(context, R.raw.shi2)
            "是", "士","氏","示","世" -> mp = MediaPlayer.create(context, R.raw.shi4)
            "手"->mp = MediaPlayer.create(context,R.raw.shou3)
            "书"->mp = MediaPlayer.create(context,R.raw.shu1)
            "术"->mp = MediaPlayer.create(context,R.raw.shu4)
            "双"->mp = MediaPlayer.create(context,R.raw.shuang1)
            "水" -> mp = MediaPlayer.create(context, R.raw.shui3)
            //t
            "他", "她", "它" -> mp = MediaPlayer.create(context, R.raw.ta1)
            "太" -> mp = MediaPlayer.create(context, R.raw.tai4)
            "天" -> mp = MediaPlayer.create(context, R.raw.tian1)
            "厅" -> mp = MediaPlayer.create(context, R.raw.ting1)
            "土" -> mp = MediaPlayer.create(context, R.raw.tu3)
            "屯" -> mp = MediaPlayer.create(context, R.raw.tun2)
            //u
            //v
            //w
            "瓦" -> mp = MediaPlayer.create(context, R.raw.wa3)
            "丸" -> mp = MediaPlayer.create(context, R.raw.wan2)
            "万" -> mp = MediaPlayer.create(context, R.raw.wan4)
            "亡", "王" -> mp = MediaPlayer.create(context, R.raw.wang2)
            "为"->mp = MediaPlayer.create(context,R.raw.wei2)
            "卫","未" -> mp = MediaPlayer.create(context, R.raw.wei4)
            "文"->mp = MediaPlayer.create(context,R.raw.wen2)
            "我" -> mp = MediaPlayer.create(context, R.raw.wo3)
            "乌"->mp = MediaPlayer.create(context,R.raw.wu1)
            "无" -> mp = MediaPlayer.create(context, R.raw.wu2)
            "五","午" -> mp = MediaPlayer.create(context, R.raw.wu3)
            "勿"->mp = MediaPlayer.create(context,R.raw.wu4)
            //x
            "夕" -> mp = MediaPlayer.create(context, R.raw.xi1)
            "习" -> mp = MediaPlayer.create(context, R.raw.xi2)
            "下" -> mp = MediaPlayer.create(context, R.raw.xia4)
            "乡" -> mp = MediaPlayer.create(context, R.raw.xiang1)
            "小" -> mp = MediaPlayer.create(context, R.raw.xiao3)
            "心"->mp = MediaPlayer.create(context,R.raw.xin1)
            "凶"->mp = MediaPlayer.create(context,R.raw.xiong1)
            //y
            "牙" -> mp = MediaPlayer.create(context, R.raw.ya2)
            "也" -> mp = MediaPlayer.create(context, R.raw.ye3)
            "尤" -> mp = MediaPlayer.create(context, R.raw.you2)
            "友" -> mp = MediaPlayer.create(context, R.raw.you3)
            "又" -> mp = MediaPlayer.create(context, R.raw.you4)
            "一" -> mp = MediaPlayer.create(context, R.raw.yi1)
            "乙", "已","以" -> mp = MediaPlayer.create(context, R.raw.yi3)
            "亿", "义", "艺","忆" -> mp = MediaPlayer.create(context, R.raw.yi4)
            "引"-> mp = MediaPlayer.create(context,R.raw.yin3)
            "于" -> mp = MediaPlayer.create(context, R.raw.yu2)
            "与","予" -> mp = MediaPlayer.create(context, R.raw.yu3)
            "玉"->mp = MediaPlayer.create(context,R.raw.yu4)
            "元" -> mp = MediaPlayer.create(context, R.raw.yuan2)
            "月"->mp = MediaPlayer.create(context,R.raw.yue4)
            "云","匀" -> mp = MediaPlayer.create(context, R.raw.yun2)
            "允"-> mp = MediaPlayer.create(context,R.raw.yun3)
            //z
            "扎" -> mp = MediaPlayer.create(context, R.raw.zha1)
            "丈" -> mp = MediaPlayer.create(context, R.raw.zhang4)
            "正"-> mp = MediaPlayer.create(context,R.raw.zheng4)
            "之", "支" -> mp = MediaPlayer.create(context, R.raw.zhi1)
            "止" -> mp = MediaPlayer.create(context, R.raw.zhi3)
            "中" -> mp = MediaPlayer.create(context, R.raw.zhong1)
            "爪"->mp = MediaPlayer.create(context,R.raw.zhua3)
            "专" -> mp = MediaPlayer.create(context, R.raw.zhuan1)
            "子" -> mp = MediaPlayer.create(context, R.raw.zi3)
            "祖" -> mp = MediaPlayer.create(context, R.raw.zu3)
            else -> mp = MediaPlayer.create(context, R.raw.mou3)
        }
        //控制语速
        val pp = PlaybackParams()
        pp.speed = 1.5f
        mp.playbackParams = pp

        mp.stop()
        return mp;
    }
}