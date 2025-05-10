import { registerPlugin } from '@capacitor/core';
const NativeVideo = registerPlugin('NativeVideo', {
    web: () => import('./web').then((m) => new m.NativeVideoWeb()),
});
export * from './definitions';
export { NativeVideo };
//# sourceMappingURL=index.js.map