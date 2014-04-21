define([], function() {
    App.Model._ExistenciasModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'cantidad' : ''
 ,  
		 'productoId' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
			 if(name=='productoId'){  
                 var value = App.Utils.getModelFromCache('productoComponent',this.get('productoId'));
                 if(value) 
                 return value.get('name');
             }
         return this.get(name);
        }
    });

    App.Model._ExistenciasList = Backbone.Collection.extend({
        model: App.Model._ExistenciasModel,
        initialize: function() {
        }

    });
    return App.Model._ExistenciasModel;
});