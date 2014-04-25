App.Delegate.BodegaDelegate = {
    search: function(user, callback, callbackError) {
        $.ajax({
            url: '/bodega.service.subsystem.web/webresources/Bodega/search',
            type: 'POST',
            data: JSON.stringify(user),
            contentType: 'application/json'
        }).done(_.bind(function(data) {
            callback(data);
        }, this)).error(_.bind(function(data) {
            callbackError(data);
        }, this));
    }
};