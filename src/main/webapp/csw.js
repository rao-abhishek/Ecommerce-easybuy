/**
 * 
 */
self.addEventListener('push', function(e) {
  
   var timestamp = new Date().getTime() +  2 * 6000;
  var options = {
	
    body: 'This notification was generated from a push!',
    icon: 'images/example.png',
    vibrate: [100, 50, 100],
  //  showTrigger: new TimestampTrigger(timestamp),
    
    data: {
      dateOfArrival: Date.now(),
      primaryKey: '2'
    },
    actions: 
    [
      {action: 'explore', title: 'Explore this new world',
        icon: 'images/checkmark.png'},
      {action: 'close', title: 'Close',
        icon: 'images/xmark.png'},
    ]
  };
  e.waitUntil(
    self.registration.showNotification('Hello world!', options)
  );
});