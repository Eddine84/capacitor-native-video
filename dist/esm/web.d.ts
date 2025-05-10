import { WebPlugin } from '@capacitor/core';
import type { NativeVideoPlugin } from './definitions';
export declare class NativeVideoWeb extends WebPlugin implements NativeVideoPlugin {
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
}
