
var push = require('web-push');
 
let vapidKeys ={
  publicKey: 'BAWkr7GaesIFA_zvYVFBmAF4FD730i94CKBtJapQmnFurGPfo_q0KLuB24OlSRtBjtNDF5ZJ9wnPDKmg7aGE2es',
  privateKey: 'MjLRBqVdsP7GA3YeogV4TuNg11B44gQgNHUfnb7ETZ8'
}

let keys = push.generateVAPIDKeys();

console.log(keys);

console.log("------***********-------------**********--------------");

push.setVapidDetails('https://www.google.com', vapidKeys.publicKey, vapidKeys.privateKey);

let sub={"endpoint":"https://fcm.googleapis.com/fcm/send/eHwJlRhogXM:APA91bFqgjbiNfxB34Qq_lkBGIu8YDu_fHhR12gViS3L1t0Z-V01QkMXTf6sOsXC5YhHOne87t0HpMFhO1_nt3ynYa0EoIgujjcHRh9WAbRCwVbhhWyAQ8kc80aACQbIv02FeqHd1PX4","expirationTime":null,"keys":{"p256dh":"BPCyIp12rANoGCdBAReEzxXuocPktxUiMHDiJPpm25fNoN4NtLeEMC9WWyDzMAm9oxolBV4t01xfbC9WC7Y6Nsw","auth":"UaScQMyNnVMwgCF6BuqiAQ"}};
console.log(vapidKeys);

console.log(push.sendNotification(sub));


