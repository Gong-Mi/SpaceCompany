Game.resourceCategoryData = (function () {

    var instance = {};

    instance.energy = {
        title: '能量',
        category: 'energy'
    };

    instance.earth = {
        class: 'collapseEarth',
        title: '地球资源',
        category: 'earth'
    };

    instance.innerSol = {
        class: 'collapseInnerPlanetary',
        title: '内行星资源',
        category: 'innerSol'
    };

    instance.outerSol = {
        class: 'collapseOuterPlanetary',
        title: '外行星资源',
        category: 'outerSol'
    };

    return instance;

}());

Game.resourceData = (function () {

    var instance = {};


    /*********************
     * Energy Resources  *
     *********************/

    instance.energy = {
        name: '能量',
        desc: '能量由蒸汽机、太阳能等动力源产生，甚至可以发展到聚变能和核能。开始时最多可储存 100,000 能量，但解锁电池后可以增加此上限。',
        icon: 'energyIcon',
        category: 'energy',
        baseCapacity: 50000,
        unlocked: false
    };

    instance.plasma = {
        name: '等离子体',
        desc: '等离子体是物质的第四种状态，被 T4 机器和大型空间结构用作公司的极端动力源。',
        icon: 'plasmaIcon',
        category: 'energy',
        baseCapacity: 50,
        unlocked: false
    };

    instance.uranium = {
        name: '铀',
        desc: '铀是主要资源之一。它用于许多事物，包括存储升级、机械和太空中的大多数事物。',
        icon: 'uraniumIcon',
        category: 'energy',
        baseCapacity: 50,
        unlocked: false
    };

    instance.lava = {
        name: '岩浆',
        desc: '岩浆难以处理且只能在火山中找到，是最难获得的资源之一。',
        icon: 'lavaIcon',
        category: 'energy',
        baseCapacity: 50,
        unlocked: false
    };

    /********************
     * Earth Resources  *
     ********************/

    instance.oil = {
        name: '石油',
        desc: '石油是从地下抽取的，用于建造 T2 资源采集器。',
        icon: 'oilIcon',
        category: 'earth',
        baseCapacity: 50,
        unlocked: false
    };

    instance.metal = {
        name: '金属',
        desc: '金属是主要资源之一。它用于许多事物，包括存储升级、机械和太空中的大多数事物。',
        icon: 'metalIcon',
        category: 'earth',
        baseCapacity: 50,
        unlocked: true
    };

    instance.gem = {
        name: '宝石',
        desc: '宝石是主要资源之一。它们用于高级机器、强大的工具和组件。它们在游戏后期更有用。',
        icon: 'gemIcon',
        category: 'earth',
        baseCapacity: 50,
        unlocked: true
    };

    instance.charcoal = {
        name: '木炭',
        desc: '木炭是二级资源，被引擎用来为你的公司生产动力。燃烧木材产生 1 个木炭。',
        icon: 'charcoalIcon',
        category: 'earth',
        baseCapacity: 50,
        unlocked: false
    };

    instance.wood = {
        name: '木头',
        desc: '木头是主要资源之一。在游戏早期常用于工具和建筑。',
        icon: 'woodIcon',
        category: 'earth',
        baseCapacity: 50,
        unlocked: true
    };

    instance.silicon = {
        name: '硅',
        desc: '硅用于第三级自动采矿系统。这些在建造你的第一个奇观时非常有用。尽管是高级资源，但主要通过加热地球上的沙子获得。',
        icon: 'siliconIcon',
        category: 'earth',
        baseCapacity: 50,
        unlocked: false
    };


    /******************************
     * Inner Planetary Resources  *
     ******************************/

    instance.lunarite = {
        name: '月球岩',
        desc: '月球岩在月球上发现，是一种地球上没有的稀有资源。它比普通金属坚固得多，但也更难获得。',
        icon: 'lunariteIcon',
        category: 'innerSol',
        baseCapacity: 50,
        unlocked: false
    };

    instance.methane = {
        name: '甲烷',
        desc: '甲烷是金星上大量存在的一种气体。它可以比固体燃料更有效地为你的公司供电。',
        icon: 'methaneIcon',
        category: 'innerSol',
        baseCapacity: 50,
        unlocked: false
    };

    instance.titanium = {
        name: '钛',
        desc: '钛主要发现于火星。它用于建造坚固的机器和甲烷发电厂。',
        icon: 'titaniumIcon',
        category: 'innerSol',
        baseCapacity: 50,
        unlocked: false
    };

    instance.gold = {
        name: '金',
        desc: '金是在小行星内部发现的金属。它用于建造一些奇观和复杂的机械。',
        icon: 'goldIcon',
        category: 'innerSol',
        baseCapacity: 50,
        unlocked: false
    };

    instance.silver = {
        name: '银',
        desc: '银是另一种最常在小行星带发现的金属。',
        icon: 'silverIcon',
        category: 'innerSol',
        baseCapacity: 50,
        unlocked: false
    };

    /******************************
     * Outer Planetary Resources  *
     ******************************/

    instance.hydrogen = {
        name: '氢',
        desc: '氢在木星和土星等气态巨行星上极为常见。',
        icon: 'hydrogenIcon',
        category: 'outerSol',
        baseCapacity: 50,
        unlocked: false
    };

    instance.helium = {
        name: '氦',
        desc: '氦是木星和土星等气态巨行星上第二常见的元素。',
        icon: 'heliumIcon',
        category: 'outerSol',
        baseCapacity: 50,
        unlocked: false
    };

    instance.ice = {
        name: '冰',
        desc: '冰，虽然可以在地球上收集，但远不如驾驶飞船飞往冥王星运回满载的冰那样有利可图。它主要用于 T4 机器所需的过冷技术。',
        icon: 'iceIcon',
        category: 'outerSol',
        baseCapacity: 50,
        unlocked: false
    };

    instance.meteorite = {
        name: '陨石',
        desc: '制造陨石只能使用比地球技术产生的更纯净的能量形式。因此，需要等离子体来制造这种强力资源。',
        icon: 'meteoriteIcon',
        category: 'outerSol',
        baseCapacity: 50,
        unlocked: false
    };

    instance.science = {
        name: '科学产出',
        desc: '科学用于研究新技术，以进一步推动游戏进度。',
        icon: 'scienceIcon',
        baseCapacity: 1000000,
        unlocked: false
    };

    return instance;
}());

Game.storageData = (function(){

    var instance = {};

    // Storage Upgrades
    var baseUpgradeData = {
        name: '存储升级:',
        unlocked: true,
        costType: COST_TYPE.FIXED,
        current: 0,
        maxLevel: -1,
        resource: undefined,
        displayNeedsUpdate: true,

        buttonText: '升级存储',


        apply: function (self) {
            if (typeof self.resource === 'undefined') {
                return;
            }
            var res = Game.resources.getResourceData(self.resource);
            res.capacity *= 2;
            res.displayNeedsUpdate = true;
            self.displayNeedsUpdate = true;
        },
    };

    /*********************
     * Energy Resources  *
     *********************/

    instance.storageUpgradePlasma = $.extend({}, baseUpgradeData, {
        desc: '升级你的等离子存储容量至',
        resource: 'plasma',
        cost: {
            'plasma': 50
        }
    });

    instance.storageUpgradeUranium = $.extend({}, baseUpgradeData, {
        desc: '升级你的铀存储容量至 ',
        resource: 'uranium',
        cost: {
            'uranium': 50,
            'lunarite': 20
        }
    });

    instance.storageUpgradeLava = $.extend({}, baseUpgradeData, {
        desc: '升级你的岩浆存储容量至 ',
        resource: 'lava',
        cost: {
            'lava': 50,
            'lunarite': 20
        }
    });

    /********************
     * Earth Resources  *
     ********************/

    instance.storageUpgradeOil = $.extend({}, baseUpgradeData, {
        desc: '升级你的石油存储容量至 ',
        resource: 'oil',
        cost: {
            'oil': 50,
            'metal': 20
        }
    });

    instance.storageUpgradeMetal = $.extend({}, baseUpgradeData, {
        desc: '升级你的金属存储容量至 ',
        resource: 'metal',
        cost: {
            'metal': 50
        }
    });

    instance.storageUpgradeGem = $.extend({}, baseUpgradeData, {
        desc: '升级你的宝石存储容量至 ',
        resource: 'gem',
        cost: {
            'gem': 50,
            'metal': 20
        }
    });

    instance.storageUpgradeCharcoal = $.extend({}, baseUpgradeData, {
        desc: '升级你的木炭存储容量至 ',
        resource: 'charcoal',
        cost: {
            'charcoal': 50,
            'metal': 20
        }
    });

    instance.storageUpgradeWood = $.extend({}, baseUpgradeData, {
        desc: '升级你的木材存储容量至 ',
        resource: 'wood',
        cost: {
            'wood': 50,
            'metal': 20
        }
    });

    instance.storageUpgradeSilicon = $.extend({}, baseUpgradeData, {
        desc: '升级你的硅存储容量至 ',
        resource: 'silicon',
        cost: {
            'silicon': 50,
            'lunarite': 20
        }
    });

    /******************************
     * Inner Planetary Resources  *
     ******************************/

    instance.storageUpgradeLunarite = $.extend({}, baseUpgradeData, {
        desc: '升级你的月球岩存储容量至 ',
        resource: 'lunarite',
        cost: {
            'lunarite': 50,
            'metal': 400
        }
    });

    instance.storageUpgradeMethane = $.extend({}, baseUpgradeData, {
        desc: '升级你的甲烷存储容量至 ',
        resource: 'methane',
        cost: {
            'methane': 50,
            'lunarite': 20
        }
    });

    instance.storageUpgradeTitanium = $.extend({}, baseUpgradeData, {
        desc: '升级你的钛存储容量至 ',
        resource: 'titanium',
        cost: {
            'titanium': 50,
            'lunarite': 20
        }
    });

    instance.storageUpgradeGold = $.extend({}, baseUpgradeData, {
        desc: '升级你的金存储容量至 ',
        resource: 'gold',
        cost: {
            'gold': 50,
            'lunarite': 20
        }
    });

    instance.storageUpgradeSilver = $.extend({}, baseUpgradeData, {
        desc: '升级你的银存储容量至 ',
        resource: 'silver',
        cost: {
            'silver': 50,
            'lunarite': 20
        }
    });

    /******************************
     * Outer Planetary Resources  *
     ******************************/

    instance.storageUpgradeHydrogen = $.extend({}, baseUpgradeData, {
        desc: '升级你的氢存储容量至 ',
        resource: 'hydrogen',
        cost: {
            'hydrogen': 50,
            'lunarite': 20
        }
    });

    instance.storageUpgradeHelium = $.extend({}, baseUpgradeData, {
        desc: '升级你的氦存储容量至 ',
        resource: 'helium',
        cost: {
            'helium': 50,
            'lunarite': 20
        }
    });

    instance.storageUpgradeIce = $.extend({}, baseUpgradeData, {
        desc: '升级你的冰存储容量至 ',
        resource: 'ice',
        cost: {
            'ice': 50,
            'lunarite': 20
        }
    });

    instance.storageUpgradeMeteorite = $.extend({}, baseUpgradeData, {
        desc: '升级你的陨石存储容量至 ',
        resource: 'meteorite',
        cost: {
            'meteorite': 50,
            'lunarite': 4
        }
    });

    return instance;
}());