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
     * ---
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
            "阿", "啊" -> mp = MediaPlayer.create(context, R.raw.a1)
            "哀", "挨", "唉" -> mp = MediaPlayer.create(context, R.raw.ai1)
            "爱" -> mp = MediaPlayer.create(context, R.raw.ai4)
            "安" -> mp = MediaPlayer.create(context, R.raw.an1)
            "岸", "按", "案" -> mp = MediaPlayer.create(context, R.raw.an4)
            "昂" -> mp = MediaPlayer.create(context, R.raw.ang2)
            "袄" -> mp = MediaPlayer.create(context, R.raw.ao3)
            "傲", "奥" -> mp = MediaPlayer.create(context, R.raw.ao4)
            //b
            "八", "巴", "扒", "吧", "疤" -> mp = MediaPlayer.create(context, R.raw.ba1)
            "拔" -> mp = MediaPlayer.create(context, R.raw.ba2)
            "坝", "爸", "罢" -> mp = MediaPlayer.create(context, R.raw.ba4)
            "白" -> mp = MediaPlayer.create(context, R.raw.bai2)
            "百", "伯", "柏" -> mp = MediaPlayer.create(context, R.raw.bai3)
            "败", "拜" -> mp = MediaPlayer.create(context, R.raw.bai4)
            "班", "般", "斑" -> mp = MediaPlayer.create(context, R.raw.ban1)
            "板", "版" -> mp = MediaPlayer.create(context, R.raw.ban3)
            "办", "半", "扮", "伴", "拌" -> mp = MediaPlayer.create(context, R.raw.ban4)
            "帮" -> mp = MediaPlayer.create(context, R.raw.bang1)
            "绑" -> mp = MediaPlayer.create(context, R.raw.bang3)
            "棒", "傍" -> mp = MediaPlayer.create(context, R.raw.bang4)
            "包", "胞", "剥" -> mp = MediaPlayer.create(context, R.raw.bao1)
            "饱", "宝", "保", "堡" -> mp = MediaPlayer.create(context, R.raw.bao3)
            "抱" -> mp = MediaPlayer.create(context, R.raw.bao4)
            "杯", "悲" -> mp = MediaPlayer.create(context, R.raw.bei1)
            "北" -> mp = MediaPlayer.create(context, R.raw.bei3)
            "贝", "备", "背", "倍", "被", "辈" -> mp = MediaPlayer.create(context, R.raw.bei4)
            "奔" -> mp = MediaPlayer.create(context, R.raw.ben1)
            "本" -> mp = MediaPlayer.create(context, R.raw.ben3)
            "笨" -> mp = MediaPlayer.create(context, R.raw.ben4)
            "逼" -> mp = MediaPlayer.create(context, R.raw.bi1)
            "比", "彼", "笔" -> mp = MediaPlayer.create(context, R.raw.bi3)
            "币", "必", "毕", "闭", "毙" -> mp = MediaPlayer.create(context, R.raw.bi4)
            "边", "编" -> mp = MediaPlayer.create(context, R.raw.bian1)
            "扁" -> mp = MediaPlayer.create(context, R.raw.bian3)
            "变", "便", "遍" -> mp = MediaPlayer.create(context, R.raw.bian4)
            "标" -> mp = MediaPlayer.create(context, R.raw.biao1)
            "表" -> mp = MediaPlayer.create(context, R.raw.biao3)
            "别" -> mp = MediaPlayer.create(context, R.raw.bie2)
            "宾" -> mp = MediaPlayer.create(context, R.raw.bin1)
            "冰", "兵" -> mp = MediaPlayer.create(context, R.raw.bing1)
            "丙", "柄", "饼" -> mp = MediaPlayer.create(context, R.raw.bing3)
            "并", "病" -> mp = MediaPlayer.create(context, R.raw.bing4)
            "拨", "波", "玻", "菠" -> mp = MediaPlayer.create(context, R.raw.bo1)
            "驳", "泊", "脖", "博" -> mp = MediaPlayer.create(context, R.raw.bo2)
            "卜", "补", "捕" -> mp = MediaPlayer.create(context, R.raw.bu3)
            "不", "布", "步", "怖", "部" -> mp = MediaPlayer.create(context, R.raw.bu4)
            //c
            "猜" -> mp = MediaPlayer.create(context, R.raw.cai1)
            "才", "材", "财", "裁" -> mp = MediaPlayer.create(context, R.raw.cai2)
            "采", "彩" -> mp = MediaPlayer.create(context, R.raw.cai3)
            "菜" -> mp = MediaPlayer.create(context, R.raw.cai4)
            "参" -> mp = MediaPlayer.create(context, R.raw.can1)
            "残", "蚕", "惭" -> mp = MediaPlayer.create(context, R.raw.can2)
            "惨" -> mp = MediaPlayer.create(context, R.raw.can3)
            "灿" -> mp = MediaPlayer.create(context, R.raw.can4)
            "仓", "舱" -> mp = MediaPlayer.create(context, R.raw.cang1)
            "草" -> mp = MediaPlayer.create(context, R.raw.cao3)
            "册", "厕", "侧", "测", "策" -> mp = MediaPlayer.create(context, R.raw.ce4)
            "层" -> mp = MediaPlayer.create(context, R.raw.ceng2)
            "叉", "差", "插" -> mp = MediaPlayer.create(context, R.raw.cha1)
            "茶", "查" -> mp = MediaPlayer.create(context, R.raw.cha2)
            "岔" -> mp = MediaPlayer.create(context, R.raw.cha4)
            "拆" -> mp = MediaPlayer.create(context, R.raw.chai1)
            "柴" -> mp = MediaPlayer.create(context, R.raw.chai2)
            "馋" -> mp = MediaPlayer.create(context, R.raw.chan2)
            "产", "铲" -> mp = MediaPlayer.create(context, R.raw.chan3)
            "昌" -> mp = MediaPlayer.create(context, R.raw.chang1)
            "长", "肠", "尝", "常", "偿" -> mp = MediaPlayer.create(context, R.raw.chang2)
            "厂", "场", "敞" -> mp = MediaPlayer.create(context, R.raw.chang3)
            "畅", "倡", "唱" -> mp = MediaPlayer.create(context, R.raw.chang4)
            "抄", "钞", "超" -> mp = MediaPlayer.create(context, R.raw.chao1)
            "朝" -> mp = MediaPlayer.create(context, R.raw.chao2)
            "吵", "炒" -> mp = MediaPlayer.create(context, R.raw.chao3)
            "车" -> mp = MediaPlayer.create(context, R.raw.che1)
            "扯" -> mp = MediaPlayer.create(context, R.raw.che3)
            "彻" -> mp = MediaPlayer.create(context, R.raw.che4)
            "臣", "尘", "辰", "沉", "陈", "晨" -> mp = MediaPlayer.create(context, R.raw.chen2)
            "衬", "趁" -> mp = MediaPlayer.create(context, R.raw.chen4)
            "称" -> mp = MediaPlayer.create(context, R.raw.cheng1)
            "成", "呈", "诚", "承", "城", "乘", "程", "惩" -> mp = MediaPlayer.create(context, R.raw.cheng2)
            "秤" -> mp = MediaPlayer.create(context, R.raw.cheng4)
            "吃" -> mp = MediaPlayer.create(context, R.raw.chi1)
            "池", "驰", "迟", "持", "匙" -> mp = MediaPlayer.create(context, R.raw.chi2)
            "尺", "齿", "耻" -> mp = MediaPlayer.create(context, R.raw.chi3)
            "斥", "赤", "翅" -> mp = MediaPlayer.create(context, R.raw.chi4)
            "冲", "充" -> mp = MediaPlayer.create(context, R.raw.chong1)
            "虫", "崇" -> mp = MediaPlayer.create(context, R.raw.chong2)
            "抽" -> mp = MediaPlayer.create(context, R.raw.chou1)
            "仇", "绸" -> mp = MediaPlayer.create(context, R.raw.chou2)
            "丑" -> mp = MediaPlayer.create(context, R.raw.chou3)
            "臭" -> mp = MediaPlayer.create(context, R.raw.chou4)
            "出", "初" -> mp = MediaPlayer.create(context, R.raw.chu1)
            "除", "厨", "锄" -> mp = MediaPlayer.create(context, R.raw.chu2)
            "础", "储" -> mp = MediaPlayer.create(context, R.raw.chu3)
            "处", "畜" -> mp = MediaPlayer.create(context, R.raw.chu4)
            "川", "穿" -> mp = MediaPlayer.create(context, R.raw.chuan1)
            "传", "船" -> mp = MediaPlayer.create(context, R.raw.chuan2)
            "喘" -> mp = MediaPlayer.create(context, R.raw.chuan3)
            "串" -> mp = MediaPlayer.create(context, R.raw.chuan4)
            "疮", "窗" -> mp = MediaPlayer.create(context, R.raw.chuang1)
            "床" -> mp = MediaPlayer.create(context, R.raw.chuang2)
            "闯" -> mp = MediaPlayer.create(context, R.raw.chuang3)
            "创" -> mp = MediaPlayer.create(context, R.raw.chuang4)
            "吹", "炊" -> mp = MediaPlayer.create(context, R.raw.chui1)
            "垂" -> mp = MediaPlayer.create(context, R.raw.chui2)
            "春" -> mp = MediaPlayer.create(context, R.raw.chun1)
            "纯", "唇" -> mp = MediaPlayer.create(context, R.raw.chun2)
            "词" -> mp = MediaPlayer.create(context, R.raw.ci2)
            "此" -> mp = MediaPlayer.create(context, R.raw.ci3)
            "次", "刺" -> mp = MediaPlayer.create(context, R.raw.ci4)
            "匆", "葱" -> mp = MediaPlayer.create(context, R.raw.cong1)
            "从", "丛" -> mp = MediaPlayer.create(context, R.raw.cong2)
            "凑" -> mp = MediaPlayer.create(context, R.raw.cou4)
            "粗" -> mp = MediaPlayer.create(context, R.raw.cu1)
            "促" -> mp = MediaPlayer.create(context, R.raw.cu4)
            "窜" -> mp = MediaPlayer.create(context, R.raw.cuan4)
            "脆" -> mp = MediaPlayer.create(context, R.raw.cui4)
            "村" -> mp = MediaPlayer.create(context, R.raw.cun1)
            "存" -> mp = MediaPlayer.create(context, R.raw.cun2)
            "寸" -> mp = MediaPlayer.create(context, R.raw.cun4)
            //d
            "搭" -> mp = MediaPlayer.create(context, R.raw.da1)
            "达", "答" -> mp = MediaPlayer.create(context, R.raw.da2)
            "打" -> mp = MediaPlayer.create(context, R.raw.da3)
            "大" -> mp = MediaPlayer.create(context, R.raw.da4)
            "呆" -> mp = MediaPlayer.create(context, R.raw.dai1)
            "逮" -> mp = MediaPlayer.create(context, R.raw.dai3)
            "代", "带", "贷", "待", "怠", "袋" -> mp = MediaPlayer.create(context, R.raw.dai4)
            "丹", "担", "单", "耽" -> mp = MediaPlayer.create(context, R.raw.dan1)
            "胆" -> mp = MediaPlayer.create(context, R.raw.dan3)
            "旦", "但", "诞", "淡", "弹", "蛋" -> mp = MediaPlayer.create(context, R.raw.dan4)
            "当" -> mp = MediaPlayer.create(context, R.raw.dang1)
            "挡", "党" -> mp = MediaPlayer.create(context, R.raw.dang3)
            "荡", "档" -> mp = MediaPlayer.create(context, R.raw.dang4)
            "刀", "叨" -> mp = MediaPlayer.create(context, R.raw.dao1)
            "导", "岛", "倒" -> mp = MediaPlayer.create(context, R.raw.dao3)
            "到", "盗", "悼", "道" -> mp = MediaPlayer.create(context, R.raw.dao4)
            "的" -> mp = MediaPlayer.create(context, R.raw.de0)
            "得" -> mp = MediaPlayer.create(context, R.raw.de2)
            "灯", "登" -> mp = MediaPlayer.create(context, R.raw.deng1)
            "等" -> mp = MediaPlayer.create(context, R.raw.deng3)
            "低", "堤" -> mp = MediaPlayer.create(context, R.raw.di1)
            "敌", "笛" -> mp = MediaPlayer.create(context, R.raw.di2)
            "抵", "底" -> mp = MediaPlayer.create(context, R.raw.di3)
            "地", "弟", "帝", "递", "第" -> mp = MediaPlayer.create(context, R.raw.di4)
            "典", "点" -> mp = MediaPlayer.create(context, R.raw.dian3)
            "电", "店", "垫" -> mp = MediaPlayer.create(context, R.raw.dian4)
            "叼" -> mp = MediaPlayer.create(context, R.raw.diao1)
            "吊", "钓", "调", "掉" -> mp = MediaPlayer.create(context, R.raw.diao4)
            "爹", "跌" -> mp = MediaPlayer.create(context, R.raw.die1)
            "丁", "叮", "盯", "钉" -> mp = MediaPlayer.create(context, R.raw.ding1)
            "顶" -> mp = MediaPlayer.create(context, R.raw.ding3)
            "订", "定" -> mp = MediaPlayer.create(context, R.raw.ding4)
            "丢" -> mp = MediaPlayer.create(context, R.raw.diu1)
            "东", "冬" -> mp = MediaPlayer.create(context, R.raw.dong1)
            "董" -> mp = MediaPlayer.create(context, R.raw.dong3)
            "动", "冻", "栋", "洞" -> mp = MediaPlayer.create(context, R.raw.dong4)
            "都" -> mp = MediaPlayer.create(context, R.raw.dou1)
            "抖", "陡" -> mp = MediaPlayer.create(context, R.raw.dou3)
            "斗", "豆", "逗" -> mp = MediaPlayer.create(context, R.raw.dou4)
            "毒", "独", "读" -> mp = MediaPlayer.create(context, R.raw.du2)
            "堵", "赌" -> mp = MediaPlayer.create(context, R.raw.du3)
            "杜", "肚", "度", "渡" -> mp = MediaPlayer.create(context, R.raw.du4)
            "短" -> mp = MediaPlayer.create(context, R.raw.duan3)
            "段", "断", "缎" -> mp = MediaPlayer.create(context, R.raw.duan4)
            "堆" -> mp = MediaPlayer.create(context, R.raw.dui1)
            "队", "对" -> mp = MediaPlayer.create(context, R.raw.dui4)
            "吨" -> mp = MediaPlayer.create(context, R.raw.dun1)
            "盾", "顿" -> mp = MediaPlayer.create(context, R.raw.dun4)
            "多" -> mp = MediaPlayer.create(context, R.raw.duo1)
            "夺" -> mp = MediaPlayer.create(context, R.raw.duo2)
            "朵" -> mp = MediaPlayer.create(context, R.raw.duo3)
            "惰" -> mp = MediaPlayer.create(context, R.raw.duo4)
            //e
            "鹅" -> mp = MediaPlayer.create(context, R.raw.e2)
            "恶", "饿" -> mp = MediaPlayer.create(context, R.raw.e4)
            "恩" -> mp = MediaPlayer.create(context, R.raw.en1)
            "儿", "而" -> mp = MediaPlayer.create(context, R.raw.er2)
            "耳" -> mp = MediaPlayer.create(context, R.raw.er3)
            "二" -> mp = MediaPlayer.create(context, R.raw.er4)
            //f
            "发" -> mp = MediaPlayer.create(context, R.raw.fa1)
            "乏", "伐", "罚", "阀" -> mp = MediaPlayer.create(context, R.raw.fa2)
            "法" -> mp = MediaPlayer.create(context, R.raw.fa3)
            "帆", "番" -> mp = MediaPlayer.create(context, R.raw.fan1)
            "凡", "泛", "烦" -> mp = MediaPlayer.create(context, R.raw.fan2)
            "反", "返" -> mp = MediaPlayer.create(context, R.raw.fan3)
            "犯", "饭", "范", "贩" -> mp = MediaPlayer.create(context, R.raw.fan4)
            "方" -> mp = MediaPlayer.create(context, R.raw.fang1)
            "防", "坊", "妨", "房" -> mp = MediaPlayer.create(context, R.raw.fang2)
            "仿", "访", "纺" -> mp = MediaPlayer.create(context, R.raw.fang3)
            "放" -> mp = MediaPlayer.create(context, R.raw.fang4)
            "飞", "非" -> mp = MediaPlayer.create(context, R.raw.fei1)
            "肥" -> mp = MediaPlayer.create(context, R.raw.fei2)
            "匪" -> mp = MediaPlayer.create(context, R.raw.fei3)
            "肺", "废", "沸", "费" -> mp = MediaPlayer.create(context, R.raw.fei4)
            "分", "吩", "纷" -> mp = MediaPlayer.create(context, R.raw.fen1)
            "坟" -> mp = MediaPlayer.create(context, R.raw.fen2)
            "粉" -> mp = MediaPlayer.create(context, R.raw.fen3)
            "份", "奋", "粪", "愤" -> mp = MediaPlayer.create(context, R.raw.fen4)
            "丰", "风", "封", "疯", "峰", "锋" -> mp = MediaPlayer.create(context, R.raw.feng1)
            "逢" -> mp = MediaPlayer.create(context, R.raw.feng2)
            "讽" -> mp = MediaPlayer.create(context, R.raw.feng3)
            "凤", "奉" -> mp = MediaPlayer.create(context, R.raw.feng4)
            "佛" -> mp = MediaPlayer.create(context, R.raw.fo2)
            "否" -> mp = MediaPlayer.create(context, R.raw.fou3)
            "夫", "肤" -> mp = MediaPlayer.create(context, R.raw.fu1)
            "伏", "扶", "服", "俘", "浮", "符", "幅" -> mp = MediaPlayer.create(context, R.raw.fu2)
            "抚", "斧", "府", "俯", "辅" -> mp = MediaPlayer.create(context, R.raw.fu3)
            "父", "付", "负", "妇", "附", "咐", "赴", "复", "副", "傅", "富" -> mp =
                MediaPlayer.create(context, R.raw.fu4)
            //g
            "该" -> mp = MediaPlayer.create(context, R.raw.gai1)
            "改" -> mp = MediaPlayer.create(context, R.raw.gai3)
            "盖", "溉" -> mp = MediaPlayer.create(context, R.raw.gai4)
            "甘", "肝", "竿" -> mp = MediaPlayer.create(context, R.raw.gan1)
            "杆", "秆", "赶", "敢" -> mp = MediaPlayer.create(context, R.raw.gan3)
            "干" -> mp = MediaPlayer.create(context, R.raw.gan4)
            "冈", "刚", "纲", "钢", "缸" -> mp = MediaPlayer.create(context, R.raw.gang1)
            "岗", "港" -> mp = MediaPlayer.create(context, R.raw.gang3)
            "杠" -> mp = MediaPlayer.create(context, R.raw.gang4)
            "高" -> mp = MediaPlayer.create(context, R.raw.gao1)
            "告" -> mp = MediaPlayer.create(context, R.raw.gao4)
            "哥", "胳", "鸽", "搁", "割" -> mp = MediaPlayer.create(context, R.raw.ge1)
            "革", "阁", "格", "隔" -> mp = MediaPlayer.create(context, R.raw.ge2)
            "葛" -> mp = MediaPlayer.create(context, R.raw.ge3)
            "个", "各" -> mp = MediaPlayer.create(context, R.raw.ge4)
            "给" -> mp = MediaPlayer.create(context, R.raw.gei3)
            "根" -> mp = MediaPlayer.create(context, R.raw.gen1)
            "更", "耕" -> mp = MediaPlayer.create(context, R.raw.geng1)
            "工", "弓", "公", "功", "攻", "宫", "恭", "躬" -> mp = MediaPlayer.create(context, R.raw.gong1)
            "巩" -> mp = MediaPlayer.create(context, R.raw.gong3)
            "共", "贡", "供" -> mp = MediaPlayer.create(context, R.raw.gong4)
            "勾", "沟", "钩" -> mp = MediaPlayer.create(context, R.raw.gou1)
            "狗" -> mp = MediaPlayer.create(context, R.raw.gou3)
            "构", "购", "够" -> mp = MediaPlayer.create(context, R.raw.gou4)
            "估", "孤", "姑", "辜" -> mp = MediaPlayer.create(context, R.raw.gu1)
            "古", "谷", "股", "骨" -> mp = MediaPlayer.create(context, R.raw.gu3)
            "固", "故", "顾" -> mp = MediaPlayer.create(context, R.raw.gu4)
            "瓜", "刮" -> mp = MediaPlayer.create(context, R.raw.gua1)
            "挂" -> mp = MediaPlayer.create(context, R.raw.gua4)
            "乖" -> mp = MediaPlayer.create(context, R.raw.guai1)
            "拐" -> mp = MediaPlayer.create(context, R.raw.guai3)
            "怪" -> mp = MediaPlayer.create(context, R.raw.guai4)
            "关", "观", "官", "冠" -> mp = MediaPlayer.create(context, R.raw.guan1)
            "馆" -> mp = MediaPlayer.create(context, R.raw.guan3)
            "贯", "惯" -> mp = MediaPlayer.create(context, R.raw.guan4)
            "光" -> mp = MediaPlayer.create(context, R.raw.guang1)
            "广" -> mp = MediaPlayer.create(context, R.raw.guang3)
            "归", "龟", "规" -> mp = MediaPlayer.create(context, R.raw.gui1)
            "轨", "鬼" -> mp = MediaPlayer.create(context, R.raw.gui3)
            "柜", "贵", "桂" -> mp = MediaPlayer.create(context, R.raw.gui4)
            "棍" -> mp = MediaPlayer.create(context, R.raw.gun4)
            "锅" -> mp = MediaPlayer.create(context, R.raw.guo1)
            "国" -> mp = MediaPlayer.create(context, R.raw.guo2)
            "果" -> mp = MediaPlayer.create(context, R.raw.guo3)
            "过" -> mp = MediaPlayer.create(context, R.raw.guo4)
            //h
            "哈" -> mp = MediaPlayer.create(context, R.raw.ha1)
            "咳" -> mp = MediaPlayer.create(context, R.raw.hai1)
            "还", "孩" -> mp = MediaPlayer.create(context, R.raw.hai2)
            "海" -> mp = MediaPlayer.create(context, R.raw.hai3)
            "害" -> mp = MediaPlayer.create(context, R.raw.hai4)
            "含", "寒" -> mp = MediaPlayer.create(context, R.raw.han2)
            "喊" -> mp = MediaPlayer.create(context, R.raw.han3)
            "汉", "汗", "旱" -> mp = MediaPlayer.create(context, R.raw.han4)
            "航" -> mp = MediaPlayer.create(context, R.raw.hang2)
            "号", "毫" -> mp = MediaPlayer.create(context, R.raw.hao2)
            "好" -> mp = MediaPlayer.create(context, R.raw.hao3)
            "耗", "浩" -> mp = MediaPlayer.create(context, R.raw.hao4)
            "喝" -> mp = MediaPlayer.create(context, R.raw.he1)
            "禾", "合", "何", "和", "河", "荷", "核", "盒" -> mp = MediaPlayer.create(context, R.raw.he2)
            "贺" -> mp = MediaPlayer.create(context, R.raw.he4)
            "黑" -> mp = MediaPlayer.create(context, R.raw.hei1)
            "痕" -> mp = MediaPlayer.create(context, R.raw.hen2)
            "很", "狠" -> mp = MediaPlayer.create(context, R.raw.hen3)
            "恨" -> mp = MediaPlayer.create(context, R.raw.hen4)
            "恒" -> mp = MediaPlayer.create(context, R.raw.heng2)
            "轰", "烘" -> mp = MediaPlayer.create(context, R.raw.hong1)
            "红", "宏", "虹", "洪" -> mp = MediaPlayer.create(context, R.raw.hong2)
            "哄" -> mp = MediaPlayer.create(context, R.raw.hong3)
            "喉", "猴" -> mp = MediaPlayer.create(context, R.raw.hou2)
            "吼" -> mp = MediaPlayer.create(context, R.raw.hou3)
            "后", "厚", "候" -> mp = MediaPlayer.create(context, R.raw.hou4)
            "乎", "呼", "忽" -> mp = MediaPlayer.create(context, R.raw.hu1)
            "狐", "胡", "壶", "湖" -> mp = MediaPlayer.create(context, R.raw.hu2)
            "虎" -> mp = MediaPlayer.create(context, R.raw.hu3)
            "互", "户" -> mp = MediaPlayer.create(context, R.raw.hu4)
            "划", "华", "哗", "猾", "滑" -> mp = MediaPlayer.create(context, R.raw.hua2)
            "化", "画", "话" -> mp = MediaPlayer.create(context, R.raw.hua4)
            "怀" -> mp = MediaPlayer.create(context, R.raw.huai2)
            "坏" -> mp = MediaPlayer.create(context, R.raw.huai4)
            "欢" -> mp = MediaPlayer.create(context, R.raw.huan1)
            "环" -> mp = MediaPlayer.create(context, R.raw.huan2)
            "缓" -> mp = MediaPlayer.create(context, R.raw.huan3)
            "幻", "换", "唤", "患" -> mp = MediaPlayer.create(context, R.raw.huan4)
            "荒", "慌" -> mp = MediaPlayer.create(context, R.raw.huang1)
            "皇", "黄" -> mp = MediaPlayer.create(context, R.raw.huang2)
            "谎" -> mp = MediaPlayer.create(context, R.raw.huang3)
            "晃" -> mp = MediaPlayer.create(context, R.raw.huang4)
            "灰", "挥", "恢", "辉" -> mp = MediaPlayer.create(context, R.raw.hui1)
            "回" -> mp = MediaPlayer.create(context, R.raw.hui2)
            "悔" -> mp = MediaPlayer.create(context, R.raw.hui3)
            "汇", "会", "绘", "贿", "惠" -> mp = MediaPlayer.create(context, R.raw.hui4)
            "昏", "婚" -> mp = MediaPlayer.create(context, R.raw.hun1)
            "浑" -> mp = MediaPlayer.create(context, R.raw.hun2)
            "混" -> mp = MediaPlayer.create(context, R.raw.hun4)
            "活" -> mp = MediaPlayer.create(context, R.raw.huo2)
            "火", "伙" -> mp = MediaPlayer.create(context, R.raw.huo3)
            "或", "货", "获", "祸", "惑" -> mp = MediaPlayer.create(context, R.raw.huo4)
            //i
            //j
            "击", "饥", "圾", "机", "肌", "鸡", "积", "基" -> mp = MediaPlayer.create(context, R.raw.ji1)
            "及", "吉", "级", "极", "即", "急", "疾", "集" -> mp = MediaPlayer.create(context, R.raw.ji2)
            "几", "己", "挤", "脊" -> mp = MediaPlayer.create(context, R.raw.ji3)
            "计", "记", "纪", "技", "忌", "际", "季", "剂", "迹", "济", "既", "继", "寄", "绩" -> mp =
                MediaPlayer.create(context, R.raw.ji4)
            "加", "夹", "佳", "家" -> mp = MediaPlayer.create(context, R.raw.jia1)
            "甲", "假" -> mp = MediaPlayer.create(context, R.raw.jia3)
            "价", "驾", "架" -> mp = MediaPlayer.create(context, R.raw.jia4)
            "尖", "奸", "歼", "坚", "间", "肩", "艰", "监", "兼" -> mp =
                MediaPlayer.create(context, R.raw.jian1)
            "拣", "茧", "俭", "捡", "检", "减", "剪" -> mp = MediaPlayer.create(context, R.raw.jian3)
            "见", "件", "建", "荐", "贱", "剑", "健", "舰", "渐", "践" -> mp =
                MediaPlayer.create(context, R.raw.jian4)
            "江", "姜", "浆" -> mp = MediaPlayer.create(context, R.raw.jiang1)
            "讲", "奖", "桨" -> mp = MediaPlayer.create(context, R.raw.jiang3)
            "匠", "降", "将" -> mp = MediaPlayer.create(context, R.raw.jiang4)
            "交", "郊", "浇", "娇", "骄", "胶", "椒", "焦" -> mp = MediaPlayer.create(context, R.raw.jiao1)
            "角", "狡", "饺", "绞", "脚", "搅" -> mp = MediaPlayer.create(context, R.raw.jiao3)
            "叫", "轿", "较", "教" -> mp = MediaPlayer.create(context, R.raw.jiao4)
            "阶", "皆", "接", "揭", "街" -> mp = MediaPlayer.create(context, R.raw.jie1)
            "节", "杰", "洁", "结", "捷" -> mp = MediaPlayer.create(context, R.raw.jie2)
            "姐" -> mp = MediaPlayer.create(context, R.raw.jie3)
            "介", "戒", "届", "界", "借" -> mp = MediaPlayer.create(context, R.raw.jie4)
            "巾", "斤", "今", "金", "津", "筋" -> mp = MediaPlayer.create(context, R.raw.jin1)
            "仅", "紧" -> mp = MediaPlayer.create(context, R.raw.jin3)
            "尽", "进", "近", "劲", "晋", "浸" -> mp = MediaPlayer.create(context, R.raw.jin4)
            "茎", "京", "经", "惊", "晶" -> mp = MediaPlayer.create(context, R.raw.jing1)
            "井", "颈", "景" -> mp = MediaPlayer.create(context, R.raw.jing3)
            "径", "净", "竞", "竟", "敬" -> mp = MediaPlayer.create(context, R.raw.jing4)
            "纠", "究", "揪" -> mp = MediaPlayer.create(context, R.raw.jiu1)
            "九", "久", "酒" -> mp = MediaPlayer.create(context, R.raw.jiu3)
            "旧", "救", "就" -> mp = MediaPlayer.create(context, R.raw.jiu4)
            "拘", "居" -> mp = MediaPlayer.create(context, R.raw.ju1)
            "局", "菊" -> mp = MediaPlayer.create(context, R.raw.ju2)
            "举" -> mp = MediaPlayer.create(context, R.raw.ju3)
            "巨", "句", "拒", "具", "矩", "俱", "剧", "据", "距", "惧" -> mp =
                MediaPlayer.create(context, R.raw.ju4)
            "捐" -> mp = MediaPlayer.create(context, R.raw.juan1)
            "卷" -> mp = MediaPlayer.create(context, R.raw.juan3)
            "倦", "绢" -> mp = MediaPlayer.create(context, R.raw.juan4)
            "决", "觉", "绝", "掘" -> mp = MediaPlayer.create(context, R.raw.jue2)
            "军", "均", "君", "菌" -> mp = MediaPlayer.create(context, R.raw.jun1)
            "俊" -> mp = MediaPlayer.create(context, R.raw.jun4)
            //k
            "卡" -> mp = MediaPlayer.create(context, R.raw.ka3)
            "开" -> mp = MediaPlayer.create(context, R.raw.kai1)
            "凯", "慨" -> mp = MediaPlayer.create(context, R.raw.kai3)
            "刊", "堪" -> mp = MediaPlayer.create(context, R.raw.kan1)
            "砍" -> mp = MediaPlayer.create(context, R.raw.kan3)
            "看" -> mp = MediaPlayer.create(context, R.raw.kan4)
            "康" -> mp = MediaPlayer.create(context, R.raw.kang1)
            "扛" -> mp = MediaPlayer.create(context, R.raw.kang2)
            "抗", "炕" -> mp = MediaPlayer.create(context, R.raw.kang4)
            "考", "烤" -> mp = MediaPlayer.create(context, R.raw.kao3)
            "科", "棵" -> mp = MediaPlayer.create(context, R.raw.ke1)
            "可", "渴" -> mp = MediaPlayer.create(context, R.raw.ke3)
            "刻", "客", "课" -> mp = MediaPlayer.create(context, R.raw.ke4)
            "肯", "垦", "恳" -> mp = MediaPlayer.create(context, R.raw.ken3)
            "坑" -> mp = MediaPlayer.create(context, R.raw.keng1)
            "空" -> mp = MediaPlayer.create(context, R.raw.kong1)
            "孔", "恐" -> mp = MediaPlayer.create(context, R.raw.kong3)
            "控" -> mp = MediaPlayer.create(context, R.raw.kong4)
            "口" -> mp = MediaPlayer.create(context, R.raw.kou3)
            "扣", "寇" -> mp = MediaPlayer.create(context, R.raw.kou4)
            "枯", "哭" -> mp = MediaPlayer.create(context, R.raw.ku1)
            "苦" -> mp = MediaPlayer.create(context, R.raw.ku3)
            "库", "裤" -> mp = MediaPlayer.create(context, R.raw.ku4)
            "夸" -> mp = MediaPlayer.create(context, R.raw.kua1)
            "垮" -> mp = MediaPlayer.create(context, R.raw.kua3)
            "挎" -> mp = MediaPlayer.create(context, R.raw.kua4)
            "快" -> mp = MediaPlayer.create(context, R.raw.kuai4)
            "宽" -> mp = MediaPlayer.create(context, R.raw.kuan1)
            "款" -> mp = MediaPlayer.create(context, R.raw.kuan3)
            "筐" -> mp = MediaPlayer.create(context, R.raw.kuang1)
            "狂" -> mp = MediaPlayer.create(context, R.raw.kuang2)
            "旷", "况", "矿", "框" -> mp = MediaPlayer.create(context, R.raw.kuang4)
            "亏" -> mp = MediaPlayer.create(context, R.raw.kui1)
            "葵" -> mp = MediaPlayer.create(context, R.raw.kui2)
            "愧" -> mp = MediaPlayer.create(context, R.raw.kui4)
            "昆" -> mp = MediaPlayer.create(context, R.raw.kun1)
            "捆" -> mp = MediaPlayer.create(context, R.raw.kun3)
            "困" -> mp = MediaPlayer.create(context, R.raw.kun4)
            "扩", "括", "阔" -> mp = MediaPlayer.create(context, R.raw.kuo4)
            //l
            "垃", "拉", "啦" -> mp = MediaPlayer.create(context, R.raw.la1)
            "喇" -> mp = MediaPlayer.create(context, R.raw.la3)
            "腊" -> mp = MediaPlayer.create(context, R.raw.la4)
            "来" -> mp = MediaPlayer.create(context, R.raw.lai2)
            "兰", "拦", "栏" -> mp = MediaPlayer.create(context, R.raw.lan2)
            "览" -> mp = MediaPlayer.create(context, R.raw.lan3)
            "烂" -> mp = MediaPlayer.create(context, R.raw.lan4)
            "郎", "狼", "廊" -> mp = MediaPlayer.create(context, R.raw.lang2)
            "朗" -> mp = MediaPlayer.create(context, R.raw.lang3)
            "浪" -> mp = MediaPlayer.create(context, R.raw.lang4)
            "捞" -> mp = MediaPlayer.create(context, R.raw.lao1)
            "牢" -> mp = MediaPlayer.create(context, R.raw.lao2)
            "老", "姥" -> mp = MediaPlayer.create(context, R.raw.lao3)
            "涝" -> mp = MediaPlayer.create(context, R.raw.lao4)
            "了" -> mp = MediaPlayer.create(context, R.raw.le0)
            "乐", "勒" -> mp = MediaPlayer.create(context, R.raw.le4)
            "垒" -> mp = MediaPlayer.create(context, R.raw.lei3)
            "泪", "类", "累" -> mp = MediaPlayer.create(context, R.raw.lei4)
            "冷" -> mp = MediaPlayer.create(context, R.raw.leng3)
            "厘", "狸", "离", "梨", "犁" -> mp = MediaPlayer.create(context, R.raw.li2)
            "礼", "李", "里", "理" -> mp = MediaPlayer.create(context, R.raw.li3)
            "力", "历", "厉", "立", "丽", "励", "利", "例", "隶", "栗", "粒" -> mp =
                MediaPlayer.create(context, R.raw.li4)
            "俩" -> mp = MediaPlayer.create(context, R.raw.lia3)
            "连", "怜", "帘", "莲", "联" -> mp = MediaPlayer.create(context, R.raw.lian2)
            "脸" -> mp = MediaPlayer.create(context, R.raw.lian3)
            "练", "炼", "恋", "链" -> mp = MediaPlayer.create(context, R.raw.lian4)
            "良", "凉", "梁" -> mp = MediaPlayer.create(context, R.raw.liang2)
            "两" -> mp = MediaPlayer.create(context, R.raw.liang3)
            "亮", "谅", "辆", "量" -> mp = MediaPlayer.create(context, R.raw.liang4)
            "辽", "疗" -> mp = MediaPlayer.create(context, R.raw.liao2)
            "料" -> mp = MediaPlayer.create(context, R.raw.liao4)
            "列", "劣", "烈", "猎", "裂" -> mp = MediaPlayer.create(context, R.raw.lie4)
            "邻", "林", "临", "淋" -> mp = MediaPlayer.create(context, R.raw.lin2)
            "伶", "灵", "铃", "陵" -> mp = MediaPlayer.create(context, R.raw.ling2)
            "岭", "领" -> mp = MediaPlayer.create(context, R.raw.ling3)
            "另", "令" -> mp = MediaPlayer.create(context, R.raw.ling4)
            "刘", "留", "流" -> mp = MediaPlayer.create(context, R.raw.liu2)
            "柳" -> mp = MediaPlayer.create(context, R.raw.liu3)
            "六" -> mp = MediaPlayer.create(context, R.raw.liu4)
            "龙", "聋", "笼", "隆" -> mp = MediaPlayer.create(context, R.raw.long2)
            "拢", "垄" -> mp = MediaPlayer.create(context, R.raw.long3)
            "搂" -> mp = MediaPlayer.create(context, R.raw.lou3)
            "炉" -> mp = MediaPlayer.create(context, R.raw.lu2)
            "虏", "鲁" -> mp = MediaPlayer.create(context, R.raw.lu3)
            "陆", "录", "鹿" -> mp = MediaPlayer.create(context, R.raw.lu4)
            "卵" -> mp = MediaPlayer.create(context, R.raw.luan3)
            "乱" -> mp = MediaPlayer.create(context, R.raw.luan4)
            "掠", "略" -> mp = MediaPlayer.create(context, R.raw.lue4)
            "轮" -> mp = MediaPlayer.create(context, R.raw.lun2)
            "论" -> mp = MediaPlayer.create(context, R.raw.lun4)
            "罗", "萝" -> mp = MediaPlayer.create(context, R.raw.luo2)
            "络", "骆", "落" -> mp = MediaPlayer.create(context, R.raw.luo4)
            "驴" -> mp = MediaPlayer.create(context, R.raw.lv2)
            "旅", "屡" -> mp = MediaPlayer.create(context, R.raw.lv3)
            "律", "虑", "率", "绿" -> mp = MediaPlayer.create(context, R.raw.lv4)
            //m
            "吗" -> mp = MediaPlayer.create(context, R.raw.ma0)
            "妈" -> mp = MediaPlayer.create(context, R.raw.ma1)
            "麻" -> mp = MediaPlayer.create(context, R.raw.ma2)
            "马", "码", "蚂" -> mp = MediaPlayer.create(context, R.raw.ma3)
            "骂" -> mp = MediaPlayer.create(context, R.raw.ma4)
            "埋" -> mp = MediaPlayer.create(context, R.raw.mai2)
            "买" -> mp = MediaPlayer.create(context, R.raw.mai3)
            "迈", "麦", "卖", "脉" -> mp = MediaPlayer.create(context, R.raw.mai4)
            "蛮" -> mp = MediaPlayer.create(context, R.raw.man2)
            "芒", "忙", "盲", "茫" -> mp = MediaPlayer.create(context, R.raw.mang2)
            "猫" -> mp = MediaPlayer.create(context, R.raw.mao1)
            "毛", "矛", "茅" -> mp = MediaPlayer.create(context, R.raw.mao2)
            "茂", "冒", "贸", "帽" -> mp = MediaPlayer.create(context, R.raw.mao4)
            "么" -> mp = MediaPlayer.create(context, R.raw.me0)
            "没", "眉", "梅" -> mp = MediaPlayer.create(context, R.raw.mei2)
            "每", "美" -> mp = MediaPlayer.create(context, R.raw.mei3)
            "妹" -> mp = MediaPlayer.create(context, R.raw.mei4)
            "们" -> mp = MediaPlayer.create(context, R.raw.men0)
            "门" -> mp = MediaPlayer.create(context, R.raw.men2)
            "闷" -> mp = MediaPlayer.create(context, R.raw.men4)
            "萌" -> mp = MediaPlayer.create(context, R.raw.meng2)
            "猛" -> mp = MediaPlayer.create(context, R.raw.meng3)
            "孟", "梦" -> mp = MediaPlayer.create(context, R.raw.meng4)
            "眯" -> mp = MediaPlayer.create(context, R.raw.mi1)
            "迷", "谜" -> mp = MediaPlayer.create(context, R.raw.mi2)
            "米" -> mp = MediaPlayer.create(context, R.raw.mi3)
            "秘", "密" -> mp = MediaPlayer.create(context, R.raw.mi4)
            "眠", "绵", "棉" -> mp = MediaPlayer.create(context, R.raw.mian2)
            "免", "勉" -> mp = MediaPlayer.create(context, R.raw.mian3)
            "面" -> mp = MediaPlayer.create(context, R.raw.mian4)
            "苗", "描" -> mp = MediaPlayer.create(context, R.raw.miao2)
            "秒" -> mp = MediaPlayer.create(context, R.raw.miao3)
            "妙", "庙" -> mp = MediaPlayer.create(context, R.raw.miao4)
            "灭" -> mp = MediaPlayer.create(context, R.raw.mie4)
            "民" -> mp = MediaPlayer.create(context, R.raw.min2)
            "敏" -> mp = MediaPlayer.create(context, R.raw.min3)
            "名", "明", "鸣" -> mp = MediaPlayer.create(context, R.raw.ming2)
            "命" -> mp = MediaPlayer.create(context, R.raw.ming4)
            "抹" -> mp = MediaPlayer.create(context, R.raw.mo3)
            "末", "沫", "莫" -> mp = MediaPlayer.create(context, R.raw.mo4)
            "谋" -> mp = MediaPlayer.create(context, R.raw.mou2)
            "某" -> mp = MediaPlayer.create(context, R.raw.mou3)
            "母", "亩" -> mp = MediaPlayer.create(context, R.raw.mu3)
            "木", "目", "牧" -> mp = MediaPlayer.create(context, R.raw.mu4)
            //n
            "拿" -> mp = MediaPlayer.create(context, R.raw.na2)
            "哪" -> mp = MediaPlayer.create(context, R.raw.na3)
            "那", "纳" -> mp = MediaPlayer.create(context, R.raw.na4)
            "乃", "奶" -> mp = MediaPlayer.create(context, R.raw.nai3)
            "耐" -> mp = MediaPlayer.create(context, R.raw.nai4)
            "男", "南", "难" -> mp = MediaPlayer.create(context, R.raw.nan2)
            "挠" -> mp = MediaPlayer.create(context, R.raw.nao2)
            "恼", "脑" -> mp = MediaPlayer.create(context, R.raw.nao3)
            "闹" -> mp = MediaPlayer.create(context, R.raw.nao4)
            "呢" -> mp = MediaPlayer.create(context, R.raw.ne0)
            "内" -> mp = MediaPlayer.create(context, R.raw.nei4)
            "能" -> mp = MediaPlayer.create(context, R.raw.neng2)
            "尼", "泥" -> mp = MediaPlayer.create(context, R.raw.ni2)
            "你" -> mp = MediaPlayer.create(context, R.raw.ni3)
            "逆" -> mp = MediaPlayer.create(context, R.raw.ni4)
            "年", "粘" -> mp = MediaPlayer.create(context, R.raw.nian2)
            "念" -> mp = MediaPlayer.create(context, R.raw.nian4)
            "娘" -> mp = MediaPlayer.create(context, R.raw.niang2)
            "鸟" -> mp = MediaPlayer.create(context, R.raw.niao3)
            "尿" -> mp = MediaPlayer.create(context, R.raw.niao4)
            "捏" -> mp = MediaPlayer.create(context, R.raw.nie1)
            "您" -> mp = MediaPlayer.create(context, R.raw.nin2)
            "宁" -> mp = MediaPlayer.create(context, R.raw.ning2)
            "牛" -> mp = MediaPlayer.create(context, R.raw.niu2)
            "纽" -> mp = MediaPlayer.create(context, R.raw.niu3)
            "农", "浓" -> mp = MediaPlayer.create(context, R.raw.nong2)
            "弄" -> mp = MediaPlayer.create(context, R.raw.nong4)
            "奴" -> mp = MediaPlayer.create(context, R.raw.nu2)
            "怒" -> mp = MediaPlayer.create(context, R.raw.nu4)
            "挪" -> mp = MediaPlayer.create(context, R.raw.nuo2)
            "女", "努" -> mp = MediaPlayer.create(context, R.raw.nv3)
            //o
            "欧" -> mp = MediaPlayer.create(context, R.raw.ou1)
            "偶" -> mp = MediaPlayer.create(context, R.raw.ou3)
            //p
            "趴" -> mp = MediaPlayer.create(context, R.raw.pa1)
            "爬" -> mp = MediaPlayer.create(context, R.raw.pa2)
            "怕" -> mp = MediaPlayer.create(context, R.raw.pa4)
            "拍" -> mp = MediaPlayer.create(context, R.raw.pai1)
            "排", "牌" -> mp = MediaPlayer.create(context, R.raw.pai2)
            "派" -> mp = MediaPlayer.create(context, R.raw.pai4)
            "盘" -> mp = MediaPlayer.create(context, R.raw.pan2)
            "判", "盼", "叛" -> mp = MediaPlayer.create(context, R.raw.pan4)
            "乓" -> mp = MediaPlayer.create(context, R.raw.pang1)
            "旁" -> mp = MediaPlayer.create(context, R.raw.pang2)
            "胖" -> mp = MediaPlayer.create(context, R.raw.pang4)
            "抛" -> mp = MediaPlayer.create(context, R.raw.pao1)
            "袍" -> mp = MediaPlayer.create(context, R.raw.pao2)
            "跑" -> mp = MediaPlayer.create(context, R.raw.pao3)
            "泡", "炮" -> mp = MediaPlayer.create(context, R.raw.pao4)
            "陪", "培", "赔" -> mp = MediaPlayer.create(context, R.raw.pei2)
            "佩", "配" -> mp = MediaPlayer.create(context, R.raw.pei4)
            "喷" -> mp = MediaPlayer.create(context, R.raw.pen1)
            "盆" -> mp = MediaPlayer.create(context, R.raw.pen2)
            "朋", "棚" -> mp = MediaPlayer.create(context, R.raw.peng2)
            "捧" -> mp = MediaPlayer.create(context, R.raw.peng3)
            "批", "披" -> mp = MediaPlayer.create(context, R.raw.pi1)
            "皮", "疲", "脾" -> mp = MediaPlayer.create(context, R.raw.pi2)
            "匹" -> mp = MediaPlayer.create(context, R.raw.pi3)
            "偏" -> mp = MediaPlayer.create(context, R.raw.pian1)
            "片", "骗" -> mp = MediaPlayer.create(context, R.raw.pian4)
            "票" -> mp = MediaPlayer.create(context, R.raw.piao4)
            "拼" -> mp = MediaPlayer.create(context, R.raw.pin1)
            "贫" -> mp = MediaPlayer.create(context, R.raw.pin2)
            "品" -> mp = MediaPlayer.create(context, R.raw.pin3)
            "乒" -> mp = MediaPlayer.create(context, R.raw.ping1)
            "平", "评", "苹", "凭", "瓶", "萍" -> mp = MediaPlayer.create(context, R.raw.ping2)
            "坡", "泼" -> mp = MediaPlayer.create(context, R.raw.po1)
            "婆" -> mp = MediaPlayer.create(context, R.raw.po2)
            "迫", "破" -> mp = MediaPlayer.create(context, R.raw.po4)
            "剖" -> mp = MediaPlayer.create(context, R.raw.pou1)
            "扑" -> mp = MediaPlayer.create(context, R.raw.pu1)
            "仆", "葡" -> mp = MediaPlayer.create(context, R.raw.pu2)
            "朴", "普" -> mp = MediaPlayer.create(context, R.raw.pu3)
            "铺" -> mp = MediaPlayer.create(context, R.raw.pu4)
            //q
            "七", "妻", "戚", "期", "欺" -> mp = MediaPlayer.create(context, R.raw.qi1)
            "齐", "其", "奇", "骑", "棋" -> mp = MediaPlayer.create(context, R.raw.qi2)
            "乞", "岂", "企", "启", "起" -> mp = MediaPlayer.create(context, R.raw.qi3)
            "气", "弃", "汽", "砌" -> mp = MediaPlayer.create(context, R.raw.qi4)
            "洽", "恰" -> mp = MediaPlayer.create(context, R.raw.qia4)
            "千", "迁", "牵", "铅", "谦" -> mp = MediaPlayer.create(context, R.raw.qian1)
            "前", "钱", "钳" -> mp = MediaPlayer.create(context, R.raw.qian2)
            "浅" -> mp = MediaPlayer.create(context, R.raw.qian3)
            "欠" -> mp = MediaPlayer.create(context, R.raw.qian4)
            "枪", "腔" -> mp = MediaPlayer.create(context, R.raw.qiang1)
            "强" -> mp = MediaPlayer.create(context, R.raw.qiang2)
            "抢" -> mp = MediaPlayer.create(context, R.raw.qiang3)
            "悄" -> mp = MediaPlayer.create(context, R.raw.qiao1)
            "乔", "侨", "桥" -> mp = MediaPlayer.create(context, R.raw.qiao2)
            "巧" -> mp = MediaPlayer.create(context, R.raw.qiao3)
            "切" -> mp = MediaPlayer.create(context, R.raw.qie1)
            "茄" -> mp = MediaPlayer.create(context, R.raw.qie2)
            "且" -> mp = MediaPlayer.create(context, R.raw.qie3)
            "窃" -> mp = MediaPlayer.create(context, R.raw.qie4)
            "侵", "亲" -> mp = MediaPlayer.create(context, R.raw.qin1)
            "琴", "禽" -> mp = MediaPlayer.create(context, R.raw.qin2)
            "青", "轻", "倾", "清" -> mp = MediaPlayer.create(context, R.raw.qing1)
            "情", "晴" -> mp = MediaPlayer.create(context, R.raw.qing2)
            "顷", "请" -> mp = MediaPlayer.create(context, R.raw.qing3)
            "庆" -> mp = MediaPlayer.create(context, R.raw.qing4)
            "穷" -> mp = MediaPlayer.create(context, R.raw.qiong2)
            "丘", "秋" -> mp = MediaPlayer.create(context, R.raw.qiu1)
            "求", "球" -> mp = MediaPlayer.create(context, R.raw.qiu2)
            "区", "驱", "屈", "趋" -> mp = MediaPlayer.create(context, R.raw.qu1)
            "渠" -> mp = MediaPlayer.create(context, R.raw.qu2)
            "曲", "取" -> mp = MediaPlayer.create(context, R.raw.qu3)
            "去" -> mp = MediaPlayer.create(context, R.raw.qu4)
            "圈" -> mp = MediaPlayer.create(context, R.raw.quan1)
            "权", "全", "泉", "拳" -> mp = MediaPlayer.create(context, R.raw.quan2)
            "犬" -> mp = MediaPlayer.create(context, R.raw.quan3)
            "劝", "券" -> mp = MediaPlayer.create(context, R.raw.quan4)
            "缺" -> mp = MediaPlayer.create(context, R.raw.que1)
            "雀", "确" -> mp = MediaPlayer.create(context, R.raw.que4)
            "裙" -> mp = MediaPlayer.create(context, R.raw.qun2)
            //r
            "然" -> mp = MediaPlayer.create(context, R.raw.ran2)
            "染" -> mp = MediaPlayer.create(context, R.raw.ran3)
            "让" -> mp = MediaPlayer.create(context, R.raw.rang4)
            "饶" -> mp = MediaPlayer.create(context, R.raw.rao2)
            "扰" -> mp = MediaPlayer.create(context, R.raw.rao3)
            "绕" -> mp = MediaPlayer.create(context, R.raw.rao4)
            "惹" -> mp = MediaPlayer.create(context, R.raw.re3)
            "热" -> mp = MediaPlayer.create(context, R.raw.re4)
            "人", "仁" -> mp = MediaPlayer.create(context, R.raw.ren2)
            "忍" -> mp = MediaPlayer.create(context, R.raw.ren3)
            "刃", "认", "任" -> mp = MediaPlayer.create(context, R.raw.ren4)
            "扔" -> mp = MediaPlayer.create(context, R.raw.reng1)
            "仍" -> mp = MediaPlayer.create(context, R.raw.reng2)
            "日" -> mp = MediaPlayer.create(context, R.raw.ri4)
            "荣", "绒", "容" -> mp = MediaPlayer.create(context, R.raw.rong2)
            "柔", "揉" -> mp = MediaPlayer.create(context, R.raw.rou2)
            "肉" -> mp = MediaPlayer.create(context, R.raw.rou4)
            "如" -> mp = MediaPlayer.create(context, R.raw.ru2)
            "乳", "辱" -> mp = MediaPlayer.create(context, R.raw.ru3)
            "入" -> mp = MediaPlayer.create(context, R.raw.ru4)
            "软" -> mp = MediaPlayer.create(context, R.raw.ruan3)
            "锐" -> mp = MediaPlayer.create(context, R.raw.rui4)
            "润" -> mp = MediaPlayer.create(context, R.raw.run4)
            "若", "弱" -> mp = MediaPlayer.create(context, R.raw.ruo4)
            //s
            "洒" -> mp = MediaPlayer.create(context, R.raw.sa3)
            "三" -> mp = MediaPlayer.create(context, R.raw.san1)
            "伞" -> mp = MediaPlayer.create(context, R.raw.san3)
            "散" -> mp = MediaPlayer.create(context, R.raw.san4)
            "桑" -> mp = MediaPlayer.create(context, R.raw.sang1)
            "丧" -> mp = MediaPlayer.create(context, R.raw.sang4)
            "扫", "嫂" -> mp = MediaPlayer.create(context, R.raw.sao3)
            "色" -> mp = MediaPlayer.create(context, R.raw.se4)
            "森" -> mp = MediaPlayer.create(context, R.raw.sen1)
            "杀", "沙", "纱" -> mp = MediaPlayer.create(context, R.raw.sha1)
            "厦" -> mp = MediaPlayer.create(context, R.raw.sha4)
            "筛" -> mp = MediaPlayer.create(context, R.raw.shai1)
            "晒" -> mp = MediaPlayer.create(context, R.raw.shai4)
            "山", "删", "衫" -> mp = MediaPlayer.create(context, R.raw.shan1)
            "闪", "陕" -> mp = MediaPlayer.create(context, R.raw.shan3)
            "扇", "善" -> mp = MediaPlayer.create(context, R.raw.shan4)
            "伤", "商" -> mp = MediaPlayer.create(context, R.raw.shang1)
            "晌", "赏" -> mp = MediaPlayer.create(context, R.raw.shang3)
            "上", "尚" -> mp = MediaPlayer.create(context, R.raw.shang4)
            "捎", "烧", "梢", "稍" -> mp = MediaPlayer.create(context, R.raw.shao1)
            "勺" -> mp = MediaPlayer.create(context, R.raw.shao2)
            "少" -> mp = MediaPlayer.create(context, R.raw.shao3)
            "绍", "哨" -> mp = MediaPlayer.create(context, R.raw.shao4)
            "舌", "蛇" -> mp = MediaPlayer.create(context, R.raw.she2)
            "舍" -> mp = MediaPlayer.create(context, R.raw.she3)
            "设", "社", "射", "涉" -> mp = MediaPlayer.create(context, R.raw.she4)
            "谁" -> mp = MediaPlayer.create(context, R.raw.shei2)
            "申", "伸", "身", "深" -> mp = MediaPlayer.create(context, R.raw.shen1)
            "什", "神" -> mp = MediaPlayer.create(context, R.raw.shen2)
            "沈", "审", "婶" -> mp = MediaPlayer.create(context, R.raw.shen3)
            "肾", "甚", "渗" -> mp = MediaPlayer.create(context, R.raw.shen4)
            "升", "生", "牲" -> mp = MediaPlayer.create(context, R.raw.sheng1)
            "绳" -> mp = MediaPlayer.create(context, R.raw.sheng2)
            "省" -> mp = MediaPlayer.create(context, R.raw.sheng3)
            "圣", "胜", "盛", "剩" -> mp = MediaPlayer.create(context, R.raw.sheng4)
            "尸", "失", "师", "诗", "狮", "施", "湿" -> mp = MediaPlayer.create(context, R.raw.shi1)
            "十", "石", "时", "识", "实", "拾", "食", "蚀" -> mp = MediaPlayer.create(context, R.raw.shi2)
            "史", "使", "始", "驶" -> mp = MediaPlayer.create(context, R.raw.shi3)
            "是", "士", "氏", "示", "世", "市", "式", "势", "事", "侍", "饰", "试", "视", "柿", "是", "适", "室", "逝", "释" -> mp =
                MediaPlayer.create(context, R.raw.shi4)
            "收" -> mp = MediaPlayer.create(context, R.raw.shou1)
            "手", "守", "首" -> mp = MediaPlayer.create(context, R.raw.shou3)
            "寿", "受", "授", "售", "兽" -> mp = MediaPlayer.create(context, R.raw.shou4)
            "书", "叔", "殊", "梳", "舒", "疏" -> mp = MediaPlayer.create(context, R.raw.shu1)
            "暑", "属" -> mp = MediaPlayer.create(context, R.raw.shu3)
            "术", "束", "述", "树", "竖" -> mp = MediaPlayer.create(context, R.raw.shu4)
            "刷" -> mp = MediaPlayer.create(context, R.raw.shua1)
            "耍" -> mp = MediaPlayer.create(context, R.raw.shua3)
            "衰" -> mp = MediaPlayer.create(context, R.raw.shuai1)
            "甩" -> mp = MediaPlayer.create(context, R.raw.shuai3)
            "帅" -> mp = MediaPlayer.create(context, R.raw.shuai4)
            "拴" -> mp = MediaPlayer.create(context, R.raw.shuan1)
            "双" -> mp = MediaPlayer.create(context, R.raw.shuang1)
            "爽" -> mp = MediaPlayer.create(context, R.raw.shuang3)
            "水" -> mp = MediaPlayer.create(context, R.raw.shui3)
            "税" -> mp = MediaPlayer.create(context, R.raw.shui4)
            "顺" -> mp = MediaPlayer.create(context, R.raw.shun4)
            "说" -> mp = MediaPlayer.create(context, R.raw.shuo1)
            "司", "丝", "私", "思", "斯" -> mp = MediaPlayer.create(context, R.raw.si1)
            "死" -> mp = MediaPlayer.create(context, R.raw.si3)
            "四", "寺", "似", "饲" -> mp = MediaPlayer.create(context, R.raw.si4)
            "松" -> mp = MediaPlayer.create(context, R.raw.song1)
            "宋", "送", "诵", "颂" -> mp = MediaPlayer.create(context, R.raw.song4)
            "搜" -> mp = MediaPlayer.create(context, R.raw.sou1)
            "苏" -> mp = MediaPlayer.create(context, R.raw.su1)
            "俗" -> mp = MediaPlayer.create(context, R.raw.su2)
            "诉", "肃", "素", "速", "宿" -> mp = MediaPlayer.create(context, R.raw.su4)
            "虽" -> mp = MediaPlayer.create(context, R.raw.sui1)
            "随" -> mp = MediaPlayer.create(context, R.raw.sui2)
            "岁" -> mp = MediaPlayer.create(context, R.raw.sui4)
            "孙" -> mp = MediaPlayer.create(context, R.raw.sun1)
            "损", "笋" -> mp = MediaPlayer.create(context, R.raw.sun3)
            "所", "索", "锁" -> mp = MediaPlayer.create(context, R.raw.suo3)
            //t
            "他", "她", "它" -> mp = MediaPlayer.create(context, R.raw.ta1)
            "塔" -> mp = MediaPlayer.create(context, R.raw.ta3)
            "台", "抬" -> mp = MediaPlayer.create(context, R.raw.tai2)
            "太", "态", "泰" -> mp = MediaPlayer.create(context, R.raw.tai4)
            "贪" -> mp = MediaPlayer.create(context, R.raw.tan1)
            "坛", "谈" -> mp = MediaPlayer.create(context, R.raw.tan2)
            "坦", "毯" -> mp = MediaPlayer.create(context, R.raw.tan3)
            "叹", "炭", "探" -> mp = MediaPlayer.create(context, R.raw.tan4)
            "汤" -> mp = MediaPlayer.create(context, R.raw.tang1)
            "唐", "堂" -> mp = MediaPlayer.create(context, R.raw.tang2)
            "倘" -> mp = MediaPlayer.create(context, R.raw.tang3)
            "烫" -> mp = MediaPlayer.create(context, R.raw.tang4)
            "涛", "掏" -> mp = MediaPlayer.create(context, R.raw.tao1)
            "逃", "桃", "陶", "萄", "淘" -> mp = MediaPlayer.create(context, R.raw.tao2)
            "讨" -> mp = MediaPlayer.create(context, R.raw.tao3)
            "套" -> mp = MediaPlayer.create(context, R.raw.tao4)
            "特" -> mp = MediaPlayer.create(context, R.raw.te4)
            "疼" -> mp = MediaPlayer.create(context, R.raw.teng2)
            "梯" -> mp = MediaPlayer.create(context, R.raw.ti1)
            "提" -> mp = MediaPlayer.create(context, R.raw.ti2)
            "体" -> mp = MediaPlayer.create(context, R.raw.ti3)
            "剃", "惕", "替" -> mp = MediaPlayer.create(context, R.raw.ti4)
            "天", "添" -> mp = MediaPlayer.create(context, R.raw.tian1)
            "田", "甜" -> mp = MediaPlayer.create(context, R.raw.tian2)
            "挑" -> mp = MediaPlayer.create(context, R.raw.tiao1)
            "条" -> mp = MediaPlayer.create(context, R.raw.tiao2)
            "帖", "贴" -> mp = MediaPlayer.create(context, R.raw.tie1)
            "铁" -> mp = MediaPlayer.create(context, R.raw.tie3)
            "厅", "听" -> mp = MediaPlayer.create(context, R.raw.ting1)
            "亭", "庭", "停", "蜓" -> mp = MediaPlayer.create(context, R.raw.ting2)
            "挺", "艇" -> mp = MediaPlayer.create(context, R.raw.ting3)
            "通" -> mp = MediaPlayer.create(context, R.raw.tong1)
            "同", "桐", "铜", "童" -> mp = MediaPlayer.create(context, R.raw.tong2)
            "统", "桶", "筒" -> mp = MediaPlayer.create(context, R.raw.tong3)
            "痛" -> mp = MediaPlayer.create(context, R.raw.tong4)
            "偷" -> mp = MediaPlayer.create(context, R.raw.tou1)
            "头", "投" -> mp = MediaPlayer.create(context, R.raw.tou2)
            "透" -> mp = MediaPlayer.create(context, R.raw.tou4)
            "秃", "突" -> mp = MediaPlayer.create(context, R.raw.tu1)
            "图", "徒", "途", "涂", "屠" -> mp = MediaPlayer.create(context, R.raw.tu2)
            "土", "吐" -> mp = MediaPlayer.create(context, R.raw.tu3)
            "兔" -> mp = MediaPlayer.create(context, R.raw.tu4)
            "团" -> mp = MediaPlayer.create(context, R.raw.tuan2)
            "推" -> mp = MediaPlayer.create(context, R.raw.tui1)
            "退" -> mp = MediaPlayer.create(context, R.raw.tui4)
            "吞" -> mp = MediaPlayer.create(context, R.raw.tun1)
            "屯" -> mp = MediaPlayer.create(context, R.raw.tun2)
            "托", "拖", "脱" -> mp = MediaPlayer.create(context, R.raw.tuo1)
            "驼" -> mp = MediaPlayer.create(context, R.raw.tuo2)
            "妥" -> mp = MediaPlayer.create(context, R.raw.tuo3)
            //u
            //v
            //w
            "挖", "蛙" -> mp = MediaPlayer.create(context, R.raw.wa1)
            "娃" -> mp = MediaPlayer.create(context, R.raw.wa2)
            "瓦" -> mp = MediaPlayer.create(context, R.raw.wa3)
            "袜" -> mp = MediaPlayer.create(context, R.raw.wa4)
            "歪" -> mp = MediaPlayer.create(context, R.raw.wai1)
            "外" -> mp = MediaPlayer.create(context, R.raw.wai4)
            "弯", "湾" -> mp = MediaPlayer.create(context, R.raw.wan1)
            "丸", "完", "玩", "顽" -> mp = MediaPlayer.create(context, R.raw.wan2)
            "挽", "晚" -> mp = MediaPlayer.create(context, R.raw.wan3)
            "万" -> mp = MediaPlayer.create(context, R.raw.wan4)
            "汪" -> mp = MediaPlayer.create(context, R.raw.wang1)
            "亡", "王" -> mp = MediaPlayer.create(context, R.raw.wang2)
            "网", "往" -> mp = MediaPlayer.create(context, R.raw.wang3)
            "妄", "忘", "旺", "望" -> mp = MediaPlayer.create(context, R.raw.wang4)
            "危", "威" -> mp = MediaPlayer.create(context, R.raw.wei1)
            "为", "违", "围", "唯", "维" -> mp = MediaPlayer.create(context, R.raw.wei2)
            "伟", "伪", "尾", "委" -> mp = MediaPlayer.create(context, R.raw.wei3)
            "卫", "未", "位", "味", "畏", "胃", "喂" -> mp = MediaPlayer.create(context, R.raw.wei4)
            "温" -> mp = MediaPlayer.create(context, R.raw.wen1)
            "文", "纹", "闻", "蚊" -> mp = MediaPlayer.create(context, R.raw.wen2)
            "问" -> mp = MediaPlayer.create(context, R.raw.wen4)
            "翁" -> mp = MediaPlayer.create(context, R.raw.weng1)
            "窝" -> mp = MediaPlayer.create(context, R.raw.wo1)
            "我" -> mp = MediaPlayer.create(context, R.raw.wo3)
            "沃", "卧", "握" -> mp = MediaPlayer.create(context, R.raw.wo4)
            "乌", "污", "呜", "屋" -> mp = MediaPlayer.create(context, R.raw.wu1)
            "无", "吴" -> mp = MediaPlayer.create(context, R.raw.wu2)
            "五", "午", "伍", "武", "侮" -> mp = MediaPlayer.create(context, R.raw.wu3)
            "勿", "务", "物", "误", "悟" -> mp = MediaPlayer.create(context, R.raw.wu4)
            //x
            "夕", "西", "吸", "希", "析", "牺", "息", "悉", "惜", "稀" -> mp =
                MediaPlayer.create(context, R.raw.xi1)
            "习", "席", "袭" -> mp = MediaPlayer.create(context, R.raw.xi2)
            "洗", "喜" -> mp = MediaPlayer.create(context, R.raw.xi3)
            "戏", "系", "细", "隙" -> mp = MediaPlayer.create(context, R.raw.xi4)
            "虾" -> mp = MediaPlayer.create(context, R.raw.xia1)
            "峡", "狭" -> mp = MediaPlayer.create(context, R.raw.xia2)
            "下", "吓", "夏" -> mp = MediaPlayer.create(context, R.raw.xia4)
            "仙", "先", "纤", "掀" -> mp = MediaPlayer.create(context, R.raw.xian1)
            "闲", "贤", "弦", "咸", "衔" -> mp = MediaPlayer.create(context, R.raw.xian2)
            "显", "险" -> mp = MediaPlayer.create(context, R.raw.xian3)
            "县", "现", "限", "线", "宪", "陷", "馅", "羡" -> mp = MediaPlayer.create(context, R.raw.xian4)
            "乡", "相", "香" -> mp = MediaPlayer.create(context, R.raw.xiang1)
            "详", "祥" -> mp = MediaPlayer.create(context, R.raw.xiang2)
            "享", "响" -> mp = MediaPlayer.create(context, R.raw.xiang3)
            "向", "项", "巷", "象" -> mp = MediaPlayer.create(context, R.raw.xiang4)
            "消", "宵", "销" -> mp = MediaPlayer.create(context, R.raw.xiao1)
            "小", "晓" -> mp = MediaPlayer.create(context, R.raw.xiao3)
            "孝", "校", "笑", "效" -> mp = MediaPlayer.create(context, R.raw.xiao4)
            "些" -> mp = MediaPlayer.create(context, R.raw.xie1)
            "协", "邪", "胁", "斜" -> mp = MediaPlayer.create(context, R.raw.xie2)
            "写" -> mp = MediaPlayer.create(context, R.raw.xie3)
            "泄", "泻", "卸", "屑", "械", "谢" -> mp = MediaPlayer.create(context, R.raw.xie4)
            "心", "辛", "欣" -> mp = MediaPlayer.create(context, R.raw.xin1)
            "信" -> mp = MediaPlayer.create(context, R.raw.xin4)
            "星" -> mp = MediaPlayer.create(context, R.raw.xing1)
            "刑", "行", "形", "型" -> mp = MediaPlayer.create(context, R.raw.xing2)
            "兴", "杏", "幸", "性", "姓" -> mp = MediaPlayer.create(context, R.raw.xing4)
            "凶", "兄", "胸" -> mp = MediaPlayer.create(context, R.raw.xiong1)
            "雄" -> mp = MediaPlayer.create(context, R.raw.xiong2)
            "休", "修", "羞" -> mp = MediaPlayer.create(context, R.raw.xiu1)
            "朽" -> mp = MediaPlayer.create(context, R.raw.xiu3)
            "秀", "袖", "绣", "锈" -> mp = MediaPlayer.create(context, R.raw.xiu4)
            "须", "虚" -> mp = MediaPlayer.create(context, R.raw.xu1)
            "徐" -> mp = MediaPlayer.create(context, R.raw.xu2)
            "许" -> mp = MediaPlayer.create(context, R.raw.xu3)
            "序", "叙", "绪", "续", "絮" -> mp = MediaPlayer.create(context, R.raw.xu4)
            "宣" -> mp = MediaPlayer.create(context, R.raw.xuan1)
            "悬", "旋" -> mp = MediaPlayer.create(context, R.raw.xuan2)
            "选" -> mp = MediaPlayer.create(context, R.raw.xuan3)
            "削" -> mp = MediaPlayer.create(context, R.raw.xue1)
            "穴", "学" -> mp = MediaPlayer.create(context, R.raw.xue2)
            "血", "雪" -> mp = MediaPlayer.create(context, R.raw.xue3)
            "旬", "寻", "巡", "询", "循" -> mp = MediaPlayer.create(context, R.raw.xun2)
            "训", "讯", "迅" -> mp = MediaPlayer.create(context, R.raw.xun4)
            //y
            "压", "呀", "押", "鸦", "鸭" -> mp = MediaPlayer.create(context, R.raw.ya1)
            "牙", "崖" -> mp = MediaPlayer.create(context, R.raw.ya2)
            "哑", "雅" -> mp = MediaPlayer.create(context, R.raw.ya3)
            "亚" -> mp = MediaPlayer.create(context, R.raw.ya4)
            "咽", "烟", "淹" -> mp = MediaPlayer.create(context, R.raw.yan1)
            "延", "言", "岩", "炎", "沿", "研", "盐" -> mp = MediaPlayer.create(context, R.raw.yan2)
            "掩", "眼" -> mp = MediaPlayer.create(context, R.raw.yan3)
            "厌", "艳", "宴", "验", "雁", "焰" -> mp = MediaPlayer.create(context, R.raw.yan4)
            "央", "殃", "秧" -> mp = MediaPlayer.create(context, R.raw.yang1)
            "扬", "羊", "阳", "杨", "洋" -> mp = MediaPlayer.create(context, R.raw.yang2)
            "仰", "养", "氧", "痒" -> mp = MediaPlayer.create(context, R.raw.yang3)
            "样" -> mp = MediaPlayer.create(context, R.raw.yang4)
            "妖" -> mp = MediaPlayer.create(context, R.raw.yao1)
            "窑", "谣" -> mp = MediaPlayer.create(context, R.raw.yao2)
            "咬" -> mp = MediaPlayer.create(context, R.raw.yao3)
            "药", "要", "钥" -> mp = MediaPlayer.create(context, R.raw.yao4)
            "爷" -> mp = MediaPlayer.create(context, R.raw.ye2)
            "也", "冶", "野" -> mp = MediaPlayer.create(context, R.raw.ye3)
            "业", "叶", "页", "夜", "液" -> mp = MediaPlayer.create(context, R.raw.ye4)
            "一", "衣", "医", "依" -> mp = MediaPlayer.create(context, R.raw.yi1)
            "仪", "宜", "姨", "移", "遗" -> mp = MediaPlayer.create(context, R.raw.yi2)
            "乙", "已", "以", "蚁", "倚", "椅" -> mp = MediaPlayer.create(context, R.raw.yi3)
            "亿", "义", "艺", "忆", "议", "亦", "异", "役", "译", "易", "疫", "益", "谊" -> mp =
                MediaPlayer.create(context, R.raw.yi4)
            "因", "阴", "音", "姻" -> mp = MediaPlayer.create(context, R.raw.yin1)
            "银" -> mp = MediaPlayer.create(context, R.raw.yin2)
            "引", "饮", "隐" -> mp = MediaPlayer.create(context, R.raw.yin3)
            "印" -> mp = MediaPlayer.create(context, R.raw.yin4)
            "英" -> mp = MediaPlayer.create(context, R.raw.ying1)
            "迎", "盈", "营" -> mp = MediaPlayer.create(context, R.raw.ying2)
            "应", "映", "硬" -> mp = MediaPlayer.create(context, R.raw.ying4)
            "佣", "拥", "庸" -> mp = MediaPlayer.create(context, R.raw.yong1)
            "永", "咏", "泳", "勇", "涌" -> mp = MediaPlayer.create(context, R.raw.yong3)
            "用" -> mp = MediaPlayer.create(context, R.raw.yong4)
            "优", "忧", "悠" -> mp = MediaPlayer.create(context, R.raw.you1)
            "尤", "由", "邮", "犹", "油", "游" -> mp = MediaPlayer.create(context, R.raw.you2)
            "友", "有" -> mp = MediaPlayer.create(context, R.raw.you3)
            "又", "右", "幼", "诱" -> mp = MediaPlayer.create(context, R.raw.you4)
            "于", "余", "鱼", "娱", "渔", "愉" -> mp = MediaPlayer.create(context, R.raw.yu2)
            "与", "予", "屿", "宇", "羽", "雨", "语" -> mp = MediaPlayer.create(context, R.raw.yu3)
            "玉", "育", "狱", "浴", "预", "域", "欲", "遇", "御", "裕" -> mp =
                MediaPlayer.create(context, R.raw.yu4)
            "冤" -> mp = MediaPlayer.create(context, R.raw.yuan1)
            "元", "园", "员", "原", "圆", "援", "缘" -> mp = MediaPlayer.create(context, R.raw.yuan2)
            "远" -> mp = MediaPlayer.create(context, R.raw.yuan3)
            "怨", "院" -> mp = MediaPlayer.create(context, R.raw.yuan4)
            "约" -> mp = MediaPlayer.create(context, R.raw.yue1)
            "月", "阅", "悦", "跃", "越" -> mp = MediaPlayer.create(context, R.raw.yue4)
            "晕" -> mp = MediaPlayer.create(context, R.raw.yun1)
            "云", "匀" -> mp = MediaPlayer.create(context, R.raw.yun2)
            "允" -> mp = MediaPlayer.create(context, R.raw.yun3)
            "孕", "运" -> mp = MediaPlayer.create(context, R.raw.yun4)
            //z
            "杂" -> mp = MediaPlayer.create(context, R.raw.za2)
            "灾", "栽" -> mp = MediaPlayer.create(context, R.raw.zai1)
            "仔", "载", "宰" -> mp = MediaPlayer.create(context, R.raw.zai3)
            "再", "在" -> mp = MediaPlayer.create(context, R.raw.zai4)
            "咱" -> mp = MediaPlayer.create(context, R.raw.zan2)
            "暂" -> mp = MediaPlayer.create(context, R.raw.zan4)
            "脏" -> mp = MediaPlayer.create(context, R.raw.zang1)
            "葬" -> mp = MediaPlayer.create(context, R.raw.zang4)
            "早", "枣" -> mp = MediaPlayer.create(context, R.raw.zao3)
            "皂", "灶", "造" -> mp = MediaPlayer.create(context, R.raw.zao4)
            "则", "责", "择", "泽" -> mp = MediaPlayer.create(context, R.raw.ze2)
            "贼" -> mp = MediaPlayer.create(context, R.raw.zei2)
            "怎" -> mp = MediaPlayer.create(context, R.raw.zen3)
            "曾" -> mp = MediaPlayer.create(context, R.raw.zeng1)
            "扎", "渣" -> mp = MediaPlayer.create(context, R.raw.zha1)
            "轧", "闸" -> mp = MediaPlayer.create(context, R.raw.zha2)
            "眨" -> mp = MediaPlayer.create(context, R.raw.zha3)
            "炸" -> mp = MediaPlayer.create(context, R.raw.zha4)
            "宅" -> mp = MediaPlayer.create(context, R.raw.zhai2)
            "窄" -> mp = MediaPlayer.create(context, R.raw.zhai3)
            "债" -> mp = MediaPlayer.create(context, R.raw.zhai4)
            "沾" -> mp = MediaPlayer.create(context, R.raw.zhan1)
            "斩", "盏", "展", "崭" -> mp = MediaPlayer.create(context, R.raw.zhan3)
            "占", "战", "站" -> mp = MediaPlayer.create(context, R.raw.zhan4)
            "张", "章" -> mp = MediaPlayer.create(context, R.raw.zhang1)
            "涨", "掌" -> mp = MediaPlayer.create(context, R.raw.zhang3)
            "丈", "仗", "帐", "胀" -> mp = MediaPlayer.create(context, R.raw.zhang4)
            "招", "着" -> mp = MediaPlayer.create(context, R.raw.zhao1)
            "找" -> mp = MediaPlayer.create(context, R.raw.zhao3)
            "召", "兆", "赵" -> mp = MediaPlayer.create(context, R.raw.zhao4)
            "折", "哲" -> mp = MediaPlayer.create(context, R.raw.zhe2)
            "者" -> mp = MediaPlayer.create(context, R.raw.zhe3)
            "这", "浙" -> mp = MediaPlayer.create(context, R.raw.zhe4)
            "贞", "针", "侦", "珍", "真" -> mp = MediaPlayer.create(context, R.raw.zhen1)
            "诊", "枕" -> mp = MediaPlayer.create(context, R.raw.zhen3)
            "阵", "振" -> mp = MediaPlayer.create(context, R.raw.zhen4)
            "争", "征", "睁", "筝" -> mp = MediaPlayer.create(context, R.raw.zheng1)
            "正", "证", "郑", "政", "挣", "症" -> mp = MediaPlayer.create(context, R.raw.zheng4)
            "之", "支", "汁", "只", "芝", "枝", "知", "肢", "织", "脂" -> mp =
                MediaPlayer.create(context, R.raw.zhi1)
            "执", "直", "侄", "值", "职", "植", "殖" -> mp = MediaPlayer.create(context, R.raw.zhi2)
            "止", "旨", "址", "纸", "指" -> mp = MediaPlayer.create(context, R.raw.zhi3)
            "至", "帜", "制", "质", "治", "致", "秩", "智" -> mp = MediaPlayer.create(context, R.raw.zhi4)
            "中", "忠", "终", "钟" -> mp = MediaPlayer.create(context, R.raw.zhong1)
            "肿", "种" -> mp = MediaPlayer.create(context, R.raw.zhong3)
            "众", "重" -> mp = MediaPlayer.create(context, R.raw.zhong4)
            "舟", "州", "周", "洲", "粥" -> mp = MediaPlayer.create(context, R.raw.zhou1)
            "宙", "昼", "皱" -> mp = MediaPlayer.create(context, R.raw.zhou4)
            "朱", "珠", "株", "诸", "猪", "蛛" -> mp = MediaPlayer.create(context, R.raw.zhu1)
            "竹", "逐", "烛" -> mp = MediaPlayer.create(context, R.raw.zhu2)
            "主", "煮" -> mp = MediaPlayer.create(context, R.raw.zhu3)
            "助", "住", "注", "驻", "柱", "祝", "著", "铸", "筑" -> mp =
                MediaPlayer.create(context, R.raw.zhu4)
            "抓" -> mp = MediaPlayer.create(context, R.raw.zhua1)
            "爪" -> mp = MediaPlayer.create(context, R.raw.zhua3)
            "专", "砖" -> mp = MediaPlayer.create(context, R.raw.zhuan1)
            "转" -> mp = MediaPlayer.create(context, R.raw.zhuan3)
            "庄", "装" -> mp = MediaPlayer.create(context, R.raw.zhuang1)
            "壮", "状" -> mp = MediaPlayer.create(context, R.raw.zhuang4)
            "追" -> mp = MediaPlayer.create(context, R.raw.zhui1)
            "准" -> mp = MediaPlayer.create(context, R.raw.zhun3)
            "捉", "桌" -> mp = MediaPlayer.create(context, R.raw.zhuo1)
            "浊", "啄" -> mp = MediaPlayer.create(context, R.raw.zhuo2)
            "姿", "资", "滋" -> mp = MediaPlayer.create(context, R.raw.zi1)
            "子", "紫" -> mp = MediaPlayer.create(context, R.raw.zi3)
            "自", "字" -> mp = MediaPlayer.create(context, R.raw.zi4)
            "宗", "棕" -> mp = MediaPlayer.create(context, R.raw.zong1)
            "总" -> mp = MediaPlayer.create(context, R.raw.zong3)
            "纵" -> mp = MediaPlayer.create(context, R.raw.zong4)
            "走" -> mp = MediaPlayer.create(context, R.raw.zou3)
            "奏" -> mp = MediaPlayer.create(context, R.raw.zou4)
            "租" -> mp = MediaPlayer.create(context, R.raw.zu1)
            "足", "族" -> mp = MediaPlayer.create(context, R.raw.zu2)
            "祖", "阻", "组" -> mp = MediaPlayer.create(context, R.raw.zu3)
            "钻" -> mp = MediaPlayer.create(context, R.raw.zuan4)
            "最" -> mp = MediaPlayer.create(context, R.raw.zui4)
            "尊" -> mp = MediaPlayer.create(context, R.raw.zun1)
            "昨" -> mp = MediaPlayer.create(context, R.raw.zuo2)
            "左" -> mp = MediaPlayer.create(context, R.raw.zuo3)
            "作", "坐", "座", "做" -> mp = MediaPlayer.create(context, R.raw.zuo4)
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