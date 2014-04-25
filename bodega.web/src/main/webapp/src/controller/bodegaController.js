define(['controller/_bodegaController','delegate/bodegaDelegate'], function() {
    App.Controller.BodegaController = App.Controller._BodegaController.extend({
        postInit: function(options) {
            var self = this;
            Backbone.on('bodega-model-error', function(params) {
                var error = params.error;
                Backbone.trigger(self.componentId + '-' + 'error',
                        {event: 'bodegaq-model', view: self, error: {responseText: error}});
            });
        }

    });
    return App.Controller.BodegaController;
}); 