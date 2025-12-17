Game.buildingData = (function () {

    var instance = {};

    var baseProducerBuilding = {
        type: BUILDING_TYPE.PRODUCER,
        unlocked: false,
        maxCount: Number.MAX_VALUE,
        costType: COST_TYPE.FIXED
    };

    // Energy
    instance.energyT1 = $.extend({}, baseProducerBuilding, {
        name: '木炭引擎',
        variableId: 'charcoalEngine',
        desc: '燃烧木炭产生稳定的能量源。',
        resource: 'energy',
        resourcePerSecond: {
            'energy': 2,
            'charcoal': -1
        },
        cost: {
            'metal': 50,
            'gem': 25
        }
    });

    instance.energyT2 = $.extend({}, baseProducerBuilding, {
        name: '太阳能电池板',
        variableId: 'solarPanel',
        desc: '从太阳缓慢获取能量，不消耗任何资源。',
        resource: 'energy',
        resourcePerSecond: {
            'energy': 1.5
        },
        cost: {
            'metal': 30,
            'gem': 35
        }
    });

    instance.energyT3 = $.extend({}, baseProducerBuilding, {
        name: '甲烷发电站',
        variableId: 'methaneStation',
        desc: '燃烧来自金星的强大甲烷来满足你所有的电力需求。',
        resource: 'energy',
        resourcePerSecond: {
            'energy': 23,
            'methane': -6
        },
        cost: {
            'lunarite': 110,
            'titanium': 90
        }
    });

    instance.energyT4 = $.extend({}, baseProducerBuilding, {
        name: '核电站',
        variableId: 'nuclearStation',
        desc: '使用裂变产生大量电力。',
        resource: 'energy',
        resourcePerSecond: {
            'energy': 153,
            'uranium': -7
        },
        cost: {
            'lunarite': 20000,
            'titanium': 10000
        }
    });

    instance.energyT5 = $.extend({}, baseProducerBuilding, {
        name: '岩浆发电机',
        variableId: 'magmatic',
        desc: '岩浆发电机是一种利用岩浆作为燃料发电的方法。由于岩浆的极高温度，可以一次产生大量能量。',
        resource: 'energy',
        resourcePerSecond: {
            'energy': 191,
            'lava': -11
        },
        cost: {
            'lunarite': 25000,
            'gem': 30000,
            'silver': 20000
        }
    });

    instance.energyT6 = $.extend({}, baseProducerBuilding, {
        name: '聚变反应堆',
        variableId: 'fusionReactor',
        desc: '在聚变反应中，当两个轻原子核聚合成一个较重的原子时释放能量。这就是恒星中发生的反应，能产生大量动力。',
        resource: 'energy',
        resourcePerSecond: {
            'energy': 273,
            'hydrogen': -10,
            'helium': -10
        },
        cost: {
            'lunarite': 30000,
            'titanium': 20000,
            'silicon': 15000
        }
    });

    // Plasma
    instance.plasmaT1 = $.extend({}, baseProducerBuilding, {
        name: '超级加热器',
        variableId: 'heater',
        desc: '超级加热器向氢气发射电力，将其转化为等离子物质。',
        resource: 'plasma',
        resourcePerSecond: {
            'energy': -1000,
            'hydrogen': -10,
            'plasma': 1
        },
        cost: {
            'lunarite': 75000,
            'gem': 68000,
            'silicon': 59000
        }
    });

    instance.plasmaT2 = $.extend({}, baseProducerBuilding, {
        name: '等离子坑',
        variableId: 'plasmatic',
        desc: '这个装置通过向氦气云发射高能能量束，将氦气转化为等离子体。',
        resource: 'plasma',
        resourcePerSecond: {
            'energy': -8500,
            'helium': -80,
            'plasma': 10
        },
        cost: {
            'lunarite': 810000,
            'silicon': 720000,
            'meteorite': 970
        }
    });

    instance.plasmaT3 = $.extend({}, baseProducerBuilding, {
        name: '电子浴',
        variableId: 'bath',
        desc: '沐浴在电子中。能出什么错呢？',
        resource: 'plasma',
        resourcePerSecond: {
            'energy': -15000,
            'helium': -100,
            'hydrogen': -100,
            'plasma': 140
        },
        cost: {
            'lunarite': 6200000,
            'silicon': 5900000,
            'meteorite': 12100
        }
    });

    // Uranium
    instance.uraniumT1 = $.extend({}, baseProducerBuilding, {
        name: '研磨机',
        variableId: 'grinder',
        desc: '粉碎铀矿，便于从深井中运出。',
        resource: 'uranium',
        resourcePerSecond: {
            'uranium': 1
        },
        cost: {
            'lunarite': 4000,
            'titanium': 2000,
            'gold': 2000
        }
    });

    instance.uraniumT2 = $.extend({}, baseProducerBuilding, {
        name: '立方传送器',
        variableId: 'cubic',
        desc: '这可以将地底深处的岩块传送到地面，以便更容易地开采铀。',
        resource: 'uranium',
        resourcePerSecond: {
            'energy': -40,
            'uranium': 9
        },
        cost: {
            'lunarite': 10000,
            'uranium': 80,
            'oil': 10000
        }
    });

    instance.uraniumT3 = $.extend({}, baseProducerBuilding, {
        name: '铀浓缩器',
        variableId: 'enricher',
        desc: '浓缩器提高了开采铀的质量，从而允许在你的公司中使用更多岩石中的铀。',
        resource: 'uranium',
        resourcePerSecond: {
            'energy': -180,
            'uranium': 61
        },
        cost: {
            'lunarite': 21700,
            'titanium': 23000,
            'silicon': 13500
        }
    });

    instance.uraniumT4 = $.extend({}, baseProducerBuilding, {
        name: '黄饼回收器',
        variableId: 'recycler',
        desc: '回收用过的铀，为资源提供二次利用。这极大地增加了你每秒可以使用的铀的数量。',
        resource: 'uranium',
        resourcePerSecond: {
            'energy': -436,
            'uranium': 235
        },
        cost: {
            'lunarite': 93100,
            'methane': 47000,
            'meteorite': 830
        }
    });

    // Lava
    instance.lavaT1 = $.extend({}, baseProducerBuilding, {
        name: '耐热坩埚',
        variableId: 'crucible',
        desc: '你可以使用改进的坩埚来收集岩浆并储存起来以备后用。',
        resource: 'lava',
        resourcePerSecond: {
            'lava': 1
        },
        cost: {
            'lunarite': 4000,
            'gem': 7000
        }
    });

    instance.lavaT2 = $.extend({}, baseProducerBuilding, {
        name: '岩浆提取器',
        variableId: 'extractor',
        desc: '这可以自动快速地从火山中提取岩浆。',
        resource: 'lava',
        resourcePerSecond: {
            'energy': -58,
            'lava': 7
        },
        cost: {
            'lunarite': 16000,
            'titanium': 14000,
            'silicon': 6000
        }
    });

    instance.lavaT3 = $.extend({}, baseProducerBuilding, {
        name: '火成岩挤压机',
        variableId: 'extruder',
        desc: '与其外出寻找岩浆，不如利用热量和压力自己制造岩浆更方便。',
        resource: 'lava',
        resourcePerSecond: {
            'energy': -237,
            'lava': 43
        },
        cost: {
            'lunarite': 69000,
            'titanium': 57000,
            'silicon': 39000
        }
    });

    instance.lavaT4 = $.extend({}, baseProducerBuilding, {
        name: '火山萃取器',
        variableId: 'veluptuator',
        desc: '一个痛苦的大熔炉，从开采的岩石中倾泻出岩浆。',
        resource: 'lava',
        resourcePerSecond: {
            'energy': -689,
            'lava': 187
        },
        cost: {
            'lunarite': 298000,
            'gold': 121000,
            'meteorite': 750
        }
    });

    /********************
     * Earth Resources  *
     ********************/

    // Oil
    instance.oilT1 = $.extend({}, baseProducerBuilding, {
        name: '小型泵',
        variableId: 'pump',
        desc: '建造一个小泵从地下抽取石油。',
        resource: 'oil',
        resourcePerSecond: {
            'oil': 1
        },
        cost: {
            'metal': 60,
            'gem': 20
        }
    });

    instance.oilT2 = $.extend({}, baseProducerBuilding, {
        name: '抽油机',
        variableId: 'pumpjack',
        desc: '抽油机比小型泵大得多，可以工业规模生产石油，但需要大量能源。',
        resource: 'oil',
        resourcePerSecond: {
            'energy': -4,
            'oil': 10
        },
        cost: {
            'metal': 250,
            'gem': 80,
            'oil': 50
        }
    });

    instance.oilT3 = $.extend({}, baseProducerBuilding, {
        name: '油田',
        variableId: 'oilField',
        desc: '油田是巨大的开阔空间，通常发现于沙漠中，地下有巨大的油井。',
        resource: 'oil',
        resourcePerSecond: {
            'energy': -12,
            'oil': 63
        },
        cost: {
            'lunarite': 2400,
            'titanium': 2700,
            'silicon': 3900
        }
    });

    instance.oilT4 = $.extend({}, baseProducerBuilding, {
        name: '海上钻井平台',
        variableId: 'oilRig',
        desc: '海上钻井平台是漂浮在海洋上的巨型结构，从海床下提取石油。',
        resource: 'oil',
        resourcePerSecond: {
            'energy': -44,
            'oil': 246
        },
        cost: {
            'lunarite': 19400,
            'titanium': 16800,
            'meteorite': 760
        }
    });

    // Metal
    instance.metalT1 = $.extend({}, baseProducerBuilding, {
        name: '矿工',
        variableId: 'miner',
        desc: '为你的矿工制作一把镐。',
        resource: 'metal',
        unlocked: true,
        resourcePerSecond: {
            'metal': 1
        },
        cost: {
            'metal': 10,
            'wood': 5
        }
    });

    instance.metalT2 = $.extend({}, baseProducerBuilding, {
        name: '重型钻机',
        variableId: 'heavyDrill',
        desc: '重型钻机大规模开采金属。',
        resource: 'metal',
        resourcePerSecond: {
            'energy': -2,
            'metal': 8
        },
        cost: {
            'metal': 160,
            'gem': 60,
            'oil': 50
        }
    });

    instance.metalT3 = $.extend({}, baseProducerBuilding, {
        name: '巨型钻机',
        variableId: 'gigaDrill',
        desc: '巨型钻机以极高的速度提取金属。',
        resource: 'metal',
        resourcePerSecond: {
            'energy': -9,
            'metal': 108
        },
        cost: {
            'lunarite': 2800,
            'gem': 3400,
            'silicon': 4100
        }
    });

    instance.metalT4 = $.extend({}, baseProducerBuilding, {
        name: '量子钻机',
        variableId: 'quantumDrill',
        desc: '量子钻机弯曲时空连续体，以物理上不可能的速度获取金属。',
        resource: 'metal',
        resourcePerSecond: {
            'energy': -24,
            'metal': 427
        },
        cost: {
            'lunarite': 29000,
            'gold': 18700,
            'meteorite': 900
        }
    });

    // Gem
    instance.gemT1 = $.extend({}, baseProducerBuilding, {
        name: '宝石矿工',
        variableId: 'gemMiner',
        desc: '制作一把改进的镐来开采宝石。',
        resource: 'gem',
        unlocked: true,
        resourcePerSecond: {
            'gem': 1
        },
        cost: {
            'metal': 15,
            'gem': 10
        }
    });

    instance.gemT2 = $.extend({}, baseProducerBuilding, {
        name: '高级钻机',
        variableId: 'advancedDrill',
        desc: '高级钻机大规模开采宝石。由于所需的钻头非常坚硬，它比重型钻机慢。',
        resource: 'gem',
        resourcePerSecond: {
            'energy': -2,
            'gem': 4
        },
        cost: {
            'metal': 120,
            'gem': 200,
            'oil': 60
        }
    });

    instance.gemT3 = $.extend({}, baseProducerBuilding, {
        name: '镶钻钻机',
        variableId: 'diamondDrill',
        desc: '镶钻钻机是太阳系中最坚固的钻机之一，因此可以比以往任何时候都更快地收集宝石。',
        resource: 'gem',
        resourcePerSecond: {
            'energy': -15,
            'gem': 89
        },
        cost: {
            'lunarite': 3400,
            'gem': 8000,
            'silicon': 4500
        }
    });

    instance.gemT4 = $.extend({}, baseProducerBuilding, {
        name: '卡拜钻机',
        variableId: 'carbyneDrill',
        desc: '卡拜钻机是太阳系中最坚固的钻机之一，因此可以比以往任何时候都更快地收集宝石。',
        resource: 'gem',
        resourcePerSecond: {
            'energy': -40,
            'gem': 358
        },
        cost: {
            'lunarite': 21000,
            'gem': 27000,
            'meteorite': 800
        }
    });

    // Charcoal
    instance.charcoalT1 = $.extend({}, baseProducerBuilding, {
        name: '燃木炉',
        variableId: 'woodburner',
        desc: '为你的燃木炉制作一把铲子。',
        resource: 'charcoal',
        resourcePerSecond: {
            'wood': -2,
            'charcoal': 1
        },
        cost: {
            'metal': 10,
            'wood': 5
        }
    });

    instance.charcoalT2 = $.extend({}, baseProducerBuilding, {
        name: '熔炉',
        variableId: 'furnace',
        desc: '熔炉使用电加热器产生热量，将木材转化为木炭。由于热量增加，该过程更有效率。',
        resource: 'charcoal',
        resourcePerSecond: {
            'energy': -3,
            'wood': -6,
            'charcoal': 4
        },
        cost: {
            'metal': 80,
            'wood': 40,
            'oil': 100
        }
    });

    instance.charcoalT3 = $.extend({}, baseProducerBuilding, {
        name: '工业窑炉',
        variableId: 'kiln',
        desc: '这些大型窑炉比以前制造木炭的方法有效得多，并且使用更少的木材来制造与熔炉相同数量的木炭。',
        resource: 'charcoal',
        resourcePerSecond: {
            'energy': -13,
            'wood': -56,
            'charcoal': 53
        },
        cost: {
            'lunarite': 3500,
            'gem': 6200,
            'silicon': 3800
        }
    });

    instance.charcoalT4 = $.extend({}, baseProducerBuilding, {
        name: '森林燃烧器',
        variableId: 'fryer',
        desc: '森林？什么森林？',
        resource: 'charcoal',
        resourcePerSecond: {
            'energy': -34,
            'wood': -148,
            'charcoal': 210
        },
        cost: {
            'lunarite': 15800,
            'lava': 12500,
            'meteorite': 560
        }
    });

    // Wood
    instance.woodT1 = $.extend({}, baseProducerBuilding, {
        name: '伐木工',
        variableId: 'woodcutter',
        desc: '给你的伐木工做把斧头。',
        resource: 'wood',
        unlocked: true,
        resourcePerSecond: {
            'wood': 1
        },
        cost: {
            'metal': 10,
            'wood': 5
        }
    });

    instance.woodT2 = $.extend({}, baseProducerBuilding, {
        name: '激光切割机',
        variableId: 'laserCutter',
        desc: '激光切割机比斧头切树（和手指）更快，而且产生的木材也多得多。',
        resource: 'wood',
        resourcePerSecond: {
            'energy': -4,
            'wood': 6
        },
        cost: {
            'metal': 50,
            'gem': 90,
            'oil': 40
        }
    });

    instance.woodT3 = $.extend({}, baseProducerBuilding, {
        name: '大规模森林砍伐机',
        variableId: 'deforester',
        desc: '这台机器就是我们要失去热带雨林的原因。至少我们得到了很多木头！',
        resource: 'wood',
        resourcePerSecond: {
            'energy': -16,
            'wood': 74
        },
        cost: {
            'lunarite': 3000,
            'titanium': 2700,
            'silicon': 2500
        }
    });

    instance.woodT4 = $.extend({}, baseProducerBuilding, {
        name: '生物质注入器',
        variableId: 'infuser',
        desc: '通过粉碎在地球上随处可见的无用旧材料，并将其尽可能紧密地包装，直到可以再次作为木材使用。',
        resource: 'wood',
        resourcePerSecond: {
            'energy': -43,
            'wood': 297
        },
        cost: {
            'lunarite': 16000,
            'oil': 31200,
            'meteorite': 490
        }
    });

    // Silicon
    instance.siliconT1 = $.extend({}, baseProducerBuilding, {
        name: '强化喷灯',
        variableId: 'blowtorch',
        desc: '这种类型的喷灯会立即把沙子变成硅，但规模很小。要制作它，需要外星资源。',
        resource: 'silicon',
        resourcePerSecond: {
            'silicon': 1
        },
        cost: {
            'lunarite': 150,
            'titanium': 30
        }
    });

    instance.siliconT2 = $.extend({}, baseProducerBuilding, {
        name: '海滨灼烧者',
        variableId: 'scorcher',
        desc: '这个工具几乎可以融化部分海滩，以更大规模地获得硅。',
        resource: 'silicon',
        resourcePerSecond: {
            'energy': -18,
            'silicon': 9
        },
        cost: {
            'lunarite': 500,
            'gem': 1200,
            'oil': 1600
        }
    });

    instance.siliconT3 = $.extend({}, baseProducerBuilding, {
        name: '海滩歼灭者',
        variableId: 'annihilator',
        desc: '这种大规模杀伤性武器已被重新标记，现在盘旋在海岸线上方，或者说海岸线残余物的上方。',
        resource: 'silicon',
        resourcePerSecond: {
            'energy': -53,
            'silicon': 40
        },
        cost: {
            'lunarite': 3000,
            'gem': 8300,
            'silver': 2400
        }
    });

    instance.siliconT4 = $.extend({}, baseProducerBuilding, {
        name: '沙漠毁灭者',
        variableId: 'desert',
        desc: '这艘大船绕着地球运行，聚焦于撒哈拉沙漠，撕开地球的沙子，在高温下将其变成硅。',
        resource: 'silicon',
        resourcePerSecond: {
            'energy': -138,
            'silicon': 157
        },
        cost: {
            'lunarite': 20000,
            'silicon': 17700,
            'meteorite': 400
        }
    });

    /******************************
     * Inner Planetary Resources  *
     ******************************/

    // Lunarite
    instance.lunariteT1 = $.extend({}, baseProducerBuilding, {
        name: '本地月球工人',
        variableId: 'moonWorker',
        desc: '贿赂当地工人开采你的月球岩。',
        resource: 'lunarite',
        resourcePerSecond: {
            'lunarite': 1
        },
        cost: {
            'gem': 500
        }
    });

    instance.lunariteT2 = $.extend({}, baseProducerBuilding, {
        name: '低重力钻机',
        variableId: 'moonDrill',
        desc: '这些钻机实际上是漂浮的！',
        resource: 'lunarite',
        resourcePerSecond: {
            'energy': -20,
            'lunarite': 10
        },
        cost: {
            'metal': 1000,
            'gem': 600,
            'oil': 400
        }
    });

    instance.lunariteT3 = $.extend({}, baseProducerBuilding, {
        name: '月球采石场',
        variableId: 'moonQuarry',
        desc: '这个采石场撕裂了月球表面，甚至从地球上都能看到。',
        resource: 'lunarite',
        resourcePerSecond: {
            'energy': -70,
            'lunarite': 53
        },
        cost: {
            'lunarite': 8000,
            'gem': 5000,
            'silicon': 3500
        }
    });

    instance.lunariteT4 = $.extend({}, baseProducerBuilding, {
        name: '行星挖掘机',
        variableId: 'planetExcavator',
        desc: '这台大型机器深入地球，寻找地核附近的大量月球岩矿藏。这就是月球上金属的最初来源。',
        resource: 'lunarite',
        resourcePerSecond: {
            'energy': -182,
            'lunarite': 207
        },
        cost: {
            'titanium': 45000,
            'ice': 37000,
            'meteorite': 500
        }
    });

    // Methane
    instance.methaneT1 = $.extend({}, baseProducerBuilding, {
        name: '吸尘器',
        variableId: 'vacuum',
        desc: '吸入甲烷并同时清洁星球！',
        resource: 'methane',
        resourcePerSecond: {
            'methane': 1
        },
        cost: {
            'lunarite': 50
        }
    });

    instance.methaneT2 = $.extend({}, baseProducerBuilding, {
        name: '抽吸挖掘机',
        variableId: 'suctionExcavator',
        desc: '比任何东西都吸得多！',
        resource: 'methane',
        resourcePerSecond: {
            'energy': -16,
            'methane': 8
        },
        cost: {
            'lunarite': 10000,
            'gem': 800,
            'oil': 600
        }
    });

    instance.methaneT3 = $.extend({}, baseProducerBuilding, {
        name: '太空牛养殖场',
        variableId: 'spaceCow',
        desc: '这些牛经过基因改造，可以持续产生甲烷。',
        resource: 'methane',
        resourcePerSecond: {
            'energy': -49,
            'methane': 37
        },
        cost: {
            'lunarite': 10000,
            'titanium': 9000,
            'silicon': 4100
        }
    });

    instance.methaneT4 = $.extend({}, baseProducerBuilding, {
        name: '热液喷口',
        variableId: 'vent',
        desc: '从泰坦海洋底部的深海喷口收集气体。',
        resource: 'methane',
        resourcePerSecond: {
            'energy': -132,
            'methane': 149
        },
        cost: {
            'lunarite': 52000,
            'helium': 47000,
            'meteorite': 390
        }
    });

    // Titanium
    instance.titaniumT1 = $.extend({}, baseProducerBuilding, {
        name: '探险家',
        variableId: 'explorer',
        desc: '雇佣探险家在火星表面寻找钛矿，这些钛矿被风吹暴露出来。',
        resource: 'titanium',
        resourcePerSecond: {
            'titanium': 1
        },
        cost: {
            'gem': 1000
        }
    });

    instance.titaniumT2 = $.extend({}, baseProducerBuilding, {
        name: '月球岩钻机',
        variableId: 'lunariteDrill',
        desc: '这些月球岩钻机非常强大，需要它们才能从火星地壳内部开采钛。',
        resource: 'titanium',
        resourcePerSecond: {
            'energy': -13,
            'titanium': 9
        },
        cost: {
            'lunarite': 200,
            'gem': 800,
            'oil': 1000
        }
    });

    instance.titaniumT3 = $.extend({}, baseProducerBuilding, {
        name: '五重钻机',
        variableId: 'pentaDrill',
        desc: '这是一种经过改造的采矿机，其表面有 5 个钻头。这使得每秒获得的资源量大幅增加。',
        resource: 'titanium',
        resourcePerSecond: {
            'energy': -46,
            'titanium': 49
        },
        cost: {
            'lunarite': 14000,
            'gem': 11000,
            'silicon': 5600
        }
    });

    instance.titaniumT4 = $.extend({}, baseProducerBuilding, {
        name: '泰坦钻机',
        variableId: 'titanDrill',
        desc: '据说这把强大的钻机是数千年前泰坦们亲自挥舞的。',
        resource: 'titanium',
        resourcePerSecond: {
            'energy': -123,
            'titanium': 197
        },
        cost: {
            'lunarite': 63000,
            'gold': 27000,
            'meteorite': 600
        }
    });

    // Gold
    instance.goldT1 = $.extend({}, baseProducerBuilding, {
        name: '火箭机器人',
        variableId: 'droid',
        desc: '由甲烷驱动，这个机器人侦察小行星寻找金矿。',
        resource: 'gold',
        resourcePerSecond: {
            'gold': 1
        },
        cost: {
            'gem': 200,
            'methane': 50
        }
    });

    instance.goldT2 = $.extend({}, baseProducerBuilding, {
        name: '小行星破坏者',
        variableId: 'destroyer',
        desc: '穿过小行星寻找黄金。它比简单的机器人效率高得多。',
        resource: 'gold',
        resourcePerSecond: {
            'energy': -19,
            'gold': 8
        },
        cost: {
            'lunarite': 500,
            'gem': 1500,
            'oil': 1000
        }
    });

    instance.goldT3 = $.extend({}, baseProducerBuilding, {
        name: '死星二号',
        variableId: 'deathStar',
        desc: '那不是月亮！那是一个空间站！它切割小行星以暴露其核心的所有黄金。',
        		resource: 'gold',        resourcePerSecond: {
            'energy': -81,
            'gold': 51
        },
        cost: {
            'lunarite': 17000,
            'silver': 11500,
            'silicon': 8200
        }
    });

    instance.goldT4 = $.extend({}, baseProducerBuilding, {
        name: '时间致动器',
        variableId: 'actuator',
        desc: '通过量子物理学加速时间，以产生更多的黄金。',
        resource: 'gold',
        resourcePerSecond: {
            'energy': -223,
            'gold': 211
        },
        cost: {
            'lunarite': 61000,
            'helium': 15700,
            'meteorite': 600
        }
    });

    // Silver
    instance.silverT1 = $.extend({}, baseProducerBuilding, {
        name: '侦察船',
        variableId: 'scout',
        desc: '侦察船在小行星带中搜索嵌入小行星的银片。',
        resource: 'silver',
        resourcePerSecond: {
            'silver': 1
        },
        cost: {
            'lunarite': 100,
            'titanium': 20
        }
    });

    instance.silverT2 = $.extend({}, baseProducerBuilding, {
        name: '星际激光',
        variableId: 'spaceLaser',
        desc: '切割小行星，在其核心寻找银矿。',
        resource: 'silver',
        resourcePerSecond: {
            'energy': -24,
            'silver': 13
        },
        cost: {
            'lunarite': 350,
            'gem': 900,
            'oil': 1200
        }
    });

    instance.silverT3 = $.extend({}, baseProducerBuilding, {
        name: '大伯莎',
        variableId: 'bertha',
        desc: '这艘大型太空钻机，以一千多年前建造的第一次世界大战榴弹炮命名，是一种专门为采矿小行星而设计的寻银机器。',
        resource: 'silver',
        resourcePerSecond: {
            'energy': -65,
            'silver': 53
        },
        cost: {
            'lunarite': 19500,
            'silver': 18200,
            'silicon': 11000
        }
    });

    instance.silverT4 = $.extend({}, baseProducerBuilding, {
        name: '原子加农炮',
        variableId: 'cannon',
        desc: '这门强大的加农炮围绕海王星运行，可以原子化小行星表面，显露出内部的银。',
        resource: 'silver',
        resourcePerSecond: {
            'energy': -170,
            'silver': 208
        },
        cost: {
            'lunarite': 85100,
            'oil': 93800,
            'meteorite': 520
        }
    });

    /******************************
     * Outer Planetary Resources  *
     ******************************/

    // Hydrogen
    instance.hydrogenT1 = $.extend({}, baseProducerBuilding, {
        name: '氢收集器',
        variableId: 'collector',
        desc: '这个收集器围绕木星旅行，寻找氢气储存起来带回地球。',
        resource: 'hydrogen',
        resourcePerSecond: {
            'hydrogen': 1
        },
        cost: {
            'lunarite': 6000,
            'titanium': 4800
        }
    });

    instance.hydrogenT2 = $.extend({}, baseProducerBuilding, {
        name: '气态磁铁',
        variableId: 'magnet',
        desc: '磁铁吸引氢气，增加每秒收集量。',
        resource: 'hydrogen',
        resourcePerSecond: {
            'energy': -63,
            'hydrogen': 5
        },
        cost: {
            'lunarite': 10800,
            'titanium': 9600,
            'silicon': 6600
        }
    });

    instance.hydrogenT3 = $.extend({}, baseProducerBuilding, {
        name: '电解池',
        variableId: 'eCell',
        desc: '这些电池在地球上制造，可以通过持续的能量供应将水转化为氢气。',
        resource: 'hydrogen',
        resourcePerSecond: {
            'energy': -234,
            'hydrogen': 28
        },
        cost: {
            'silver': 37200,
            'gold': 34200,
            'silicon': 25800
        }
    });

    instance.hydrogenT4 = $.extend({}, baseProducerBuilding, {
        name: '兴登堡挖掘',
        variableId: 'hindenburg',
        desc: '不知何故，它奏效了。',
        resource: 'hydrogen',
        resourcePerSecond: {
            'energy': -613,
            'hydrogen': 113
        },
        cost: {
            'lunarite': 172000,
            'methane': 134000,
            'meteorite': 710
        }
    });

    // Helium
    instance.heliumT1 = $.extend({}, baseProducerBuilding, {
        name: '氦无人机',
        variableId: 'drone',
        desc: '氦无人机侦察土星区域，挑选出氦含量高的地点，然后由它缓慢开采。',        resource: 'helium',
        resourcePerSecond: {
            'helium': 1
        },
        cost: {
            'lunarite': 8400,
            'titanium': 6000
        }
    });

    instance.heliumT2 = $.extend({}, baseProducerBuilding, {
        name: '氦油轮',
        variableId: 'tanker',
        desc: '这艘巨大的油轮装载大量氦气，并将其从土星通过太空真空运往地球。',
        resource: 'helium',
        resourcePerSecond: {
            'energy': -72,
            'helium': 11
        },
        cost: {
            'lunarite': 12600,
            'titanium': 10200,
            'silicon': 8400
        }
    });

    instance.heliumT3 = $.extend({}, baseProducerBuilding, {
        name: '形态压缩机',
        variableId: 'compressor',
        desc: '压缩机将氦气密集地压缩到一个小空间中，以便于运回地球。',
        resource: 'helium',
        resourcePerSecond: {
            'energy': -248,
            'helium': 57
        },
        cost: {
            'lunarite': 63000,
            'titanium': 43800,
            'silicon': 35400
        }
    });

    instance.heliumT4 = $.extend({}, baseProducerBuilding, {
        name: '气态巨行星撇取器',
        variableId: 'skimmer',
        desc: '带着一个大桶飞入气态巨行星的大气层是我们迄今为止最好的计划！',
        resource: 'helium',
        resourcePerSecond: {
            'energy': -670,
            'helium': 232
        },
        cost: {
            'lunarite': 255000,
            'titanium': 173000,
            'meteorite': 770
        }
    });

    // Ice
    instance.iceT1 = $.extend({}, baseProducerBuilding, {
        name: '冰镐',
        variableId: 'icePick',
        desc: '冰镐是开采冰冻水最简单的方法，虽然最便宜，但也是最慢的。',
        resource: 'ice',
        resourcePerSecond: {
            'ice': 1
        },
        cost: {
            'lunarite': 17800,
            'gem': 19300
        }
    });

    instance.iceT2 = $.extend({}, baseProducerBuilding, {
        name: '冰钻',
        variableId: 'iceDrill',
        desc: '冰钻比冰镐更有效，每秒可获得更多冰。但是，它确实需要电力。',
        resource: 'ice',
        resourcePerSecond: {
            'energy': -83,
            'ice': 9
        },
        cost: {
            'lunarite': 23900,
            'titanium': 21200,
            'silicon': 19600
        }
    });

    instance.iceT3 = $.extend({}, baseProducerBuilding, {
        name: '海洋冷冻机',
        variableId: 'freezer',
        desc: '凭借先进技术，你现在能够将地球的水转化为高品质的冰，以前只能在冥王星上找到。',
        resource: 'ice',
        resourcePerSecond: {
            'energy': -397,
            'ice': 65
        },
        cost: {
            'lunarite': 117000,
            'titanium': 86000,
            'silicon': 73000
        }
    });

    instance.iceT4 = $.extend({}, baseProducerBuilding, {
        name: '急冻先生',
        variableId: 'mrFreeze',
        desc: '这个机器人是太阳系中最酷的家伙。',
        resource: 'ice',
        resourcePerSecond: {
            'energy': -1135,
            'ice': 278
        },
        cost: {
            'wood': 379000,
            'helium': 14000,
            'meteorite': 1500
        }
    });

    // Meteorite
    instance.meteoriteT1 = $.extend({}, baseProducerBuilding, {
        name: '陨石打印机',
        variableId: 'printer',
        desc: '构建一种自动化的陨石生产方式，你无需做任何事情。',
        resource: 'meteorite',
        resourcePerSecond: {
            'plasma': -3,
            'meteorite': 1
        },
        cost: {
            'lunarite': 100000,
            'silicon': 60000
        }
    });

    instance.meteoriteT2 = $.extend({}, baseProducerBuilding, {
        name: '陨石网',
        variableId: 'web',
        desc: '陨石网使用在高放射性液体中浸泡制成的纳米纤维，使其足够坚固，可以物理捕捉小行星带的流星。需要等离子体将小行星精炼成可用的陨石矿石。',
        resource: 'meteorite',
        resourcePerSecond: {
            'plasma': -21,
            'meteorite': 8
        },
        cost: {
            'lunarite': 940000,
            'uranium': 490000,
            'silicon': 510000
        }
    });

    // Research
    instance.scienceT1 = $.extend({}, baseProducerBuilding, {
        name: '家庭科学套件',
        variableId: 'lab',
        desc: '建立一个属于你自己的小型实验室来开始产生科学点数。每个每秒产生 0.1 科学点数。',
        resource: 'science',
        resourcePerSecond: {
            'science': 0.1
        },
        cost: {
            'metal': 20,
            'gem': 15,
            'wood': 10
        }
    });

    instance.scienceT2 = $.extend({}, baseProducerBuilding, {
        name: '高中科学实验室',
        variableId: 'labT2',
        desc: '建立一个更有效的实验室，以明显更快的速度继续你对科学领域的探索。每个每秒产生 1 科学点数。',
        resource: 'science',
        resourcePerSecond: {
            'science': 1
        },
        cost: {
            'metal': 1000,
            'gem': 200,
            'wood': 500
        }
    });

    instance.scienceT3 = $.extend({}, baseProducerBuilding, {
        name: '大学实验室',
        variableId: 'labT3',
        desc: '建立一个比旧实验室更好的版本，以进一步探索科学领域。每个每秒产生 10 科学点数。',
        resource: 'science',
        resourcePerSecond: {
            'science': 10
        },
        cost: {
            'metal': 17000,
            'gem': 4700,
            'wood': 9600
        }
    });

    // Solar System
    instance.rocketFuelT1 = $.extend({}, baseProducerBuilding, {
        name: '化工厂',
        variableId: 'chemicalPlant',
        desc: '化工厂用于自动制造火箭燃料。',
        resource: 'rocketFuel',
        resourcePerSecond: {
            'rocketFuel': 0.2
        },
        cost: {
            'metal': 1000,
            'gem': 750,
            'wood': 500
        }
    });

    instance.rocketFuelT2 = $.extend({}, baseProducerBuilding, {
        name: '氧化室',
        variableId: 'oxidisation',
        desc: '氧化室比化工厂更快、更有效地制造火箭燃料。',
        resource: 'rocketFuel',
        resourcePerSecond: {
            'rocketFuel': 1.5
        },
        cost: {
            'metal': 12000,
            'gem': 8300,
            'wood': 6800
        }
    });

    instance.rocketFuelT3 = $.extend({}, baseProducerBuilding, {
        name: '肼催化剂',
        variableId: 'hydrazine',
        desc: '这些通过使用甲烷等温室气体来加速制造火箭燃料所需的化学反应。',
        resource: 'rocketFuel',
        resourcePerSecond: {
            'rocketFuel': 20
        },
        cost: {
            'titanium': 140000,
            'silicon': 96300,
            'gold': 78600
        }
    });
    
    return instance;
}());
