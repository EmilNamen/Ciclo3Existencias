define([], function() {
    App.Delegate._ProductoDelegate = App.Component.BasicComponent.extend({
        
        search: function(producto, callback, callbackError) {
            console.log('Producto Search: ');
            $.ajax({
                url: '/producto.service.subsystem.web/webresources/Producto/search',
                type: 'POST',
                data: JSON.stringify(producto),
                contentType: 'application/json'
            }).done(_.bind(function(data) {
                callback(data);
            }, this)).error(_.bind(function(data) {
                callbackError(data);
            }, this));
        }
    
    });
    
    
});