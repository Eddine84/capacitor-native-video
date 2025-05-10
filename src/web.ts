import { WebPlugin } from '@capacitor/core';

import type { NativeVideoPlugin } from './definitions';

export class NativeVideoWeb extends WebPlugin implements NativeVideoPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
