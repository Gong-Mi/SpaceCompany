/*global module:false*/
module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    // Metadata.
    pkg: grunt.file.readJSON('package.json'),
    banner: '/*! <%= pkg.title || pkg.name %> - v<%= pkg.version %> - ' +
      '<%= grunt.template.today("yyyy-mm-dd") %>\n' +
      '<%= pkg.homepage ? "* " + pkg.homepage + "\\n" : "" %>' +
      '* Authored <%= grunt.template.today("yyyy") %> <%= pkg.author.name %>;*/\n',
    // Task configuration.
    concat: {
      options: {
        banner: '<%= banner %>',
        stripBanners: true
      },
      dist: {
        src: ['variable.js', 'game.js', 'utils.js', 'updates.js', 'achievements.js', 'data/achievementsData.js', 'constants.js', 'statistics.js', 'resource.js', 'data/resourceData.js', 'building.js', 'data/buildingData.js', 'tech.js', 'data/techData.js', 'data/interstellarData.js', 'data/starData.js', 'settings.js', 'interstellar.js', 'star.js', 'rocketParts.js', 'rocket.js', 'stargaze.js', 'data/stargazeData.js', 'ui/databoundElement.js', 'ui/gameTabUI.js', 'ui/resourceObserver.js', 'ui/interstellarUI.js', 'ui/achievementUI.js', 'ui/statisticUI.js', 'ui/stargazeUI.js', 'ui/resourceUI.js', 'ui/techUI.js', 'ui/legacyUI.js', 'core.js', 'notification.js', 'saving.js', 'resources.js', 'science.js', 'solarSystem.js', 'wonder.js', 'solCenter.js'],
        dest: '<%= pkg.name %>.min.js'
      }
    },
    uglify: {
      options: {
        banner: '<%= banner %>'
      },
      dist: {
        src: '<%= concat.dist.dest %>',
        dest: '<%= pkg.name %>.min.js'
      }
    }
  });

  // These plugins provide necessary tasks.
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-uglify');

  // Default task.
  grunt.registerTask('default', ['concat', 'uglify']);

};
